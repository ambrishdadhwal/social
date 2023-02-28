package com.social.profile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@EnableDiscoveryClient
@EnableRetry
@EntityScan(basePackages = {"com.social"})
public class SocialApplication implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(SocialApplication.class, args);
	}

	/**
	 * 
	 * run will execute only once when application will UP.
	 */

	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("Command line runner executed...............");
		for (int i = 0; i < args.length; ++i)
		{
			System.out.println("args[{}]: {}" + i + " , " + args[i]);
		}

	}
}
