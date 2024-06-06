package com.social.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.social.security.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	RequestContext requestContext;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException
	{
		try
		{
			final String requestTokenHeader = request.getHeader("Authorization");

			String username = null;
			String jwtToken = null;
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
			{
				jwtToken = requestTokenHeader.substring(7);
				try
				{
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				}
				catch (IllegalArgumentException e)
				{
					logger.warn("Unable to get JWT Token");
				}
				catch (ExpiredJwtException e)
				{
					logger.warn("JWT Token has expired");
				}
			}
			else
			{
				logger.warn("JWT Token does not begin with Bearer String");
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

				if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails)))
				{
					requestContext.setEmail(userDetails.getUsername());
					requestContext.setIsactive(userDetails.isEnabled());
					requestContext.setToken(jwtToken);
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(token);
				}
			}
			chain.doFilter(request, response);
		}
		catch (Exception e)
		{
			logger.error("Could not set user authentication in security context", e);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getOutputStream().println(
				"{ "
					+ "\"error\": \"" + e.getMessage() + "\",\n "
					+ "\"time\": \"" + LocalDateTime.now() + "\",\n"
					+ "\"url\": \"" + request.getRequestURI() + "\",\n "
					+ "}");

		}
	}

}
