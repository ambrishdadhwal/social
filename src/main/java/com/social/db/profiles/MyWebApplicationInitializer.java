package com.social.db.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.WebApplicationInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

/*
 * Programmatically via WebApplicationInitializer Interface
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer
{

	@Autowired
	private ConfigurableEnvironment env;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		// env.setActiveProfiles("prod");

		// servletContext.setInitParameter("spring.profiles.active", "prod");

	}
}
