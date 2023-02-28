package com.social.profile.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.social.domain.Profile;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface IProfileService
{
	//params can be configurable in application.properties
	@Retryable(retryFor = RuntimeException.class, maxAttempts = 2, backoff = @Backoff(delay = 500))
	public Optional<Profile> saveUser(Profile user);

	@Recover
	void recover(SQLException e, String sql);

	public List<Profile> allUsers();

	public long totalSocialUsers();

	public Optional<Profile> getUser(Profile user);

}
