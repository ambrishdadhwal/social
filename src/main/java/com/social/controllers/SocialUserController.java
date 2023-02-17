package com.social.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.social.datastore.SocialDataStore;
import com.social.domain.SocialUser;
import com.social.dto.SocialUserDTO;
import com.social.mapper.SocialUserMapper;
import com.social.service.ISocialUserService;

@RestController
@RequestMapping("/user")
public class SocialUserController
{

	@Autowired(required = true)
	ISocialUserService userService;

	@GetMapping(value = "/")
	public ResponseEntity<List<SocialUserDTO>> getUsers()
	{
		List<SocialUser> users = userService.allUsers();
		return new ResponseEntity<>(users.stream().map(SocialUserMapper::convertDTO).collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SocialUserDTO> getUserById(@PathVariable String id)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Long> totalUsers()
	{
		return new ResponseEntity<>(userService.totalSocialUsers(), HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<SocialUserDTO> createUser(@RequestBody @Validated SocialUserDTO user)
	{
		SocialDataStore.addUser(user);

		Optional<SocialUser> newUser = userService.saveUser(SocialUserMapper.convert(user));
		if (newUser.isPresent())
		{
			return new ResponseEntity<>(SocialUserMapper.convertDTO(newUser.get()), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<SocialUserDTO> deleteUser(@PathVariable String id)
	{
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
