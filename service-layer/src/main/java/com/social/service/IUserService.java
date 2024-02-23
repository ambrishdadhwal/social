package com.social.service;

import com.social.domain.Profile;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface IUserService
{
	//params can be configurable in application.properties
	@Retryable(retryFor = RuntimeException.class, maxAttempts = 2, backoff = @Backoff(delay = 500))
	public Optional<Profile> saveUser(Profile user);

	@Recover
	void recover(SQLException e, String sql);

	public List<Profile> allUsers();

	public long totalSocialUsers();

	public Optional<Profile> getUser(Profile user);

	public Optional<Profile> updateUser(Profile user);

	public Optional<Profile> getUserbyId(Long userId);

	public Optional<Profile> getUserbyUserNameAndId(String userName, Long userId);

	public Optional<Profile> deleteUserById(Long userId);
}
