package com.social.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.social.exceptions.ErrorDetails;

@Component
public class SocialAuthenticationEntryPoint extends BasicAuthenticationEntryPoint
{

	@Override
	public void commence(HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException authEx) throws IOException
	{
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().println(
			"{ "
				+ "\"error\": \"" + authEx.getMessage() + "\",\n "
				+ "\"time\": \"" + LocalDateTime.now() + "\",\n"
				+ "\"url\": \"" + request.getRequestURI() + "\",\n "
				+ "}");
	}

	@Override
	public void afterPropertiesSet()
	{
		setRealmName("Social");
		super.afterPropertiesSet();
	}

}
