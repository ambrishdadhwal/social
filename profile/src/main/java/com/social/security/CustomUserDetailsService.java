package com.social.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.social.domain.SocialUser;
import com.social.service.SocialUserService;

@Component
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired
	SocialUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		List<SocialUser> allUsers = userService.allUsers();

		allUsers.removeIf(n -> !n.getEmail().equals(username));

		if (!allUsers.isEmpty())
		{
			SocialUser user = allUsers.get(0);
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
