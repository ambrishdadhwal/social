package com.social.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProfileAuthenticationEntryPoint extends BasicAuthenticationEntryPoint
{

	@Override
	public void commence(HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException authEx) throws IOException
	{
		/*	log.error("Inside ProfileAuthenticationEntryPoint.BasicAuthenticationEntryPoint .....");
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getOutputStream().println(
				"{ "
					+ "\"error\": \"" + authEx.getMessage() + "\",\n "
					+ "\"time\": \"" + LocalDateTime.now() + "\",\n"
					+ "\"url\": \"" + request.getRequestURI() + "\",\n "
					+ "}");*/

		log.error("Inside ProfileAuthenticationEntryPoint.commence ....Unauthorized error: {}", authEx.getMessage());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final Map<String, Object> body = new HashMap<>();
		body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		body.put("error", "Unauthorized");
		body.put("message", authEx.getMessage());
		body.put("path", request.getServletPath());

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}

	@Override
	public void afterPropertiesSet()
	{
		setRealmName("Social");
		super.afterPropertiesSet();
	}

}
