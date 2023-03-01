package com.social.profile.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.ProfileDTO;
import com.social.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.profile.datastore.ProfileDataStore;

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
	public ResponseEntity<ProfileDTO> getUserById(@PathVariable String id)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Long> totalUsers()
	{
		return new ResponseEntity<>(profileService.totalSocialUsers(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProfileDTO> createUser(@RequestBody @Validated ProfileDTO user)
	{
		ProfileDataStore.addUser(user);

		Optional<Profile> newUser = profileService.saveUser(ProfileMapper.convert(user));
		if (newUser.isPresent())
		{
			return new ResponseEntity<>(ProfileMapper.convertDTO(newUser.get()), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProfileDTO> deleteUser(@PathVariable String id)
	{
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
