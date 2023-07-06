package com.social.profile.controllers;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.ProfileDTO;
import com.social.profile.datastore.ProfileDataStore;
import com.social.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class ProfileController
{

	@Autowired(required = true)
	IProfileService profileService;

	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<ProfileDTO>> getUsers()
	{
		List<Profile> users = profileService.allUsers();
		return new ResponseEntity<>(users.stream().map(ProfileMapper::convertDTO).collect(Collectors.toList()), HttpStatus.OK);
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<ProfileDTO> getUserById(@PathVariable(required = true) Long id)
	{
		//only login user can access their own data
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Optional<Profile> profile = profileService.getUserbyUserNameAndId(authentication.getName(), id);

		return profile.map(value -> new ResponseEntity(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Long> totalUsers()
	{
		return new ResponseEntity<>(profileService.totalSocialUsers(), HttpStatus.OK);
	}

	@Cacheable(value = "users", key = "#user")
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProfileDTO> createUser(@RequestBody @Validated ProfileDTO user)
	{
		ProfileDataStore.addUser(user);

		Optional<Profile> newUser = profileService.saveUser(ProfileMapper.convert(user));
		return newUser.map(profile -> new ResponseEntity<>(ProfileMapper.convertDTO(profile), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProfileDTO> deleteUser(@PathVariable Long id)
	{
		profileService.deleteUserById(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
