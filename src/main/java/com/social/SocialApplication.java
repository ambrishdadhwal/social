package com.social;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
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
