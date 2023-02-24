package com.social.db.profiles;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@PropertySource(
{"classpath:persistence-${envTarget:dev}.properties"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class CustomConfig
{

	private String database;
	private String url;
	private String userName;
}
