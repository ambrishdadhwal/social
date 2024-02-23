package com.social.user.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.social.user.config.TurnOnOff;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringFoxConfig
{

	@Bean
	public OpenAPI springShopOpenAPI()
	{
		return new OpenAPI()
			.info(new Info().title("SpringShop API")
				.description("Social sample application")
				.version("v0.0.1")
				.license(new License().name("Social 1.0").url("http://springdoc.org")))
			.externalDocs(new ExternalDocumentation()
				.description("Social Wiki Documentation")
				.url("https://springshop.wiki.github.org/docs"));
	}

	@Bean(initMethod = "turnOn", destroyMethod = "turnOff")
	// @Lazy
	public TurnOnOff turnOnOff()
	{
		return new TurnOnOff();
	}

}