package com.social.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthEndPoint implements HealthIndicator
{

	@Override
	public Health health()
	{
		int errorCode = check();
		if (errorCode != 0)
		{
			return Health.down().withDetail("Error Code", errorCode).build();
		}
		Map<String, String> details = new LinkedHashMap<>();
		details.put("details", "Hey Social " + "! Everything looks good");

		return Health.up().withDetails(details).build();
	}

	public int check()
	{
		// Our logic to check health
		return 0;
	}
}
