package com.social.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

import com.social.domain.Country;
import com.social.domain.Gender;
import com.social.entity.ProfileE;
import com.social.repository.postgres.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@EnableDiscoveryClient
@EnableRetry
@EnableJpaRepositories("com.social")
@EntityScan(basePackages =
{"com.social"})
@ComponentScan(basePackages =
{"com.social"})
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceApplication implements CommandLineRunner
{

	final UserRepo userRepo;

	public static void main(String[] args)
	{
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

	/**
	 * run will execute only once when application will UP.
	 */

	@Override
	public void run(String... args) throws Exception
	{
		log.info("Admin User is Created..");

		IntStream.iterate(1, n -> n + 1).limit(100).forEach(k -> {
			userRepo.save(ProfileE.builder()
				.id(Long.valueOf(k))
				.firstName("First Name - " + k)
				.lastName("Last Name - " + k)
				.email(k + "@social.com")
				.password("password-" + k)
				.isActive(true)
				.dob(LocalDate.now())
				.country(Country.INDIA)
				.gender(Gender.NOT_INTERESTED_TO_MENTION)
				.createDateTime(LocalDateTime.now())
				.modifiedDateTime(LocalDateTime.now())
				.build());
		});

	}
}
