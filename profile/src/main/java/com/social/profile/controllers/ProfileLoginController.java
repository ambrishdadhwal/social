package com.social.profile.controllers;


import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.ProfileDTO;
import com.social.presentation.ProfileLoginDTO;
import com.social.profile.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class ProfileLoginController
{

	@Autowired(required = true)
	IProfileService userService;

	@PostMapping(value = "/")
	public ResponseEntity<ProfileDTO> createUser(@RequestBody @Validated ProfileLoginDTO user)
	{
		HttpStatus status = HttpStatus.OK;

		Optional<Profile> existingUser = userService.getUser(ProfileMapper.convert(user));

		if (existingUser.isPresent())
		{
			return new ResponseEntity<>(ProfileMapper.convertDTO(existingUser.get()), status);
		}
		else
		{
			status = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<>(status);
	}
}
