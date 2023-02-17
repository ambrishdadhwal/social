package com.social.db.profiles;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
public class SocialDBConfiguration
{

	@Autowired
	Environment env;

	@Bean
	@Profile("dev")
	public DataSource dataDevSource()
	{
		System.out.println("###...Dev Profile is active...###");
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("spring.datasource.driverClassName");
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
		return dataSourceBuilder.build();
	}

	@Bean
	@Profile("prod")
	public DataSource dataProdSource()
	{
		System.out.println("###...prod Profile is active...###");
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		//dataSourceBuilder.driverClassName("spring.datasource.driverClassName");
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
		return dataSourceBuilder.build();
	}
	
	@Bean
	@Profile("h2")
	public DataSource dataH2Source()
	{
		System.out.println("###...h2 Profile is active...###");
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		//dataSourceBuilder.driverClassName("spring.datasource.driverClassName");
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
		return dataSourceBuilder.build();
	}

	@Bean
	@ConditionalOnBean(SocialDB.class)
	public SocialDB myBeanForOthers()
	{
		System.out.println("###...@ConditionalOnBean is active...###");
		return SocialDB.builder().build();
	}

}
