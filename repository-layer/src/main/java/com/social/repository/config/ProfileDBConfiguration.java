package com.social.repository.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class ProfileDBConfiguration
{

	@Autowired
	Environment env;

	@Bean
	@Profile("dev")
	@Primary
	public DataSource dataDevSource()
	{
		log.info("###...Dev Profile is active...###");
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("spring.datasource.driverClassName");
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
		return dataSourceBuilder.build();
	}

	@Bean
	@Profile("prod")
	@Primary
	public DataSource dataProdSource()
	{
		log.info("###...prod Profile is active...###");
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		// dataSourceBuilder.driverClassName("spring.datasource.driverClassName");
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
		return dataSourceBuilder.build();
	}

	@Bean
	@Profile("h2")
	@Primary
	public DataSource dataH2Source()
	{
		log.info("###...h2 Profile is active...###");
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		// dataSourceBuilder.driverClassName("spring.datasource.driverClassName");
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
		return dataSourceBuilder.build();
	}
}
