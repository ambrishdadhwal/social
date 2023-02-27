package com.social.profile.security;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ProfileAuthenticationEntryPoint extends BasicAuthenticationEntryPoint
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
