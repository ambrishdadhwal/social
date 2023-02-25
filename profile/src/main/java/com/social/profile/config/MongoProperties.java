package com.social.profile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "baeldung.greeter")
/*
 * 
 * @ConfigurationPropertiesScan annotation required to read a specific property file
 * */
public class MongoProperties
{

	private String userName;
	private String morningMessage;
	private String afternoonMessage;
	private String eveningMessage;
	private String nightMessage;
}
