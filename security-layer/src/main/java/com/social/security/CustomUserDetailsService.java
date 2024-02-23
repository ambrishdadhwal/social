package com.social.security;


import com.social.domain.Profile;
import com.social.service.IUserService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired
	IUserService profileService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		List<Profile> allUsers = profileService.allUsers();
		System.out.println("Inside CustomUserDetailsService.loadUserByUsername .....");

		allUsers.removeIf(n -> !n.getEmail().equals(username));

		if (!allUsers.isEmpty())
		{
			Profile user = allUsers.get(0);
			return User.builder()
				.username(user.getEmail())
				// .password("$2a$12$id.6XTMnJbKfp7NaFXKfpuDplfY2fMO5IsTCvwLjRSu8DOM2OnKUS")
				.password(user.getPassword())
				.roles(user.getRoles().toArray(String[]::new))
				.build();
		}

		throw new UsernameNotFoundException("Username not found");
	}
}
