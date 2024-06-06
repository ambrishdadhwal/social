package com.social.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.social.domain.Profile;
import com.social.service.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{

	final IUserService profileService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		log.info("Inside CustomUserDetailsService.loadUserByUsername .....");
		Optional<Profile> currentUser = profileService.allUsers().stream().filter(n -> n.getEmail().equals(username)).findAny();

		if (currentUser.isPresent())
		{
			Profile user = currentUser.get();
			return UserPrincipal.create(user);
		}

		throw new UsernameNotFoundException("User not found with username :-" + username);
	}
}
