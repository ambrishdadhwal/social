package com.social.profile.bootstrap;

import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Configuration
public class SpringBootBootstrap
{

	/*
	 * If mysql.properties file present at class path , then only this bean will be loaded.
	 * */
	@Bean
	@ConditionalOnResource(resources = "classpath:mysql.properties")
	Properties additionalProperties() throws IOException
	{
		Resource resource = new ClassPathResource("/mysql.properties");
		Properties properties = PropertiesLoaderUtils.loadProperties(resource);

		return properties;
	}

	@Bean
	@ConditionalOnResource(resources = "classpath:application.properties")
	Properties additionalProperties1() throws IOException
	{
		Resource resource = new ClassPathResource("/application.properties");
		Properties properties = PropertiesLoaderUtils.loadProperties(resource);

		return properties;
	}

}
