package com.social.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.domain.SocialUser;
import com.social.dto.SocialLoginDTO;
import com.social.dto.SocialUserDTO;
import com.social.mapper.SocialUserMapper;
import com.social.service.ISocialUserService;

@RestController
@RequestMapping("/login")
public class SocialLoginController
{

	@Autowired(required = true)
	ISocialUserService userService;

	@PostMapping(value = "/")
	public ResponseEntity<SocialUserDTO> createUser(@RequestBody @Validated SocialLoginDTO user)
	{
		HttpStatus status = HttpStatus.OK;

		Optional<SocialUser> existingUser = userService.getUser(SocialUserMapper.convert(user));

		if (existingUser.isPresent())
		{
			return new ResponseEntity<>(SocialUserMapper.convertDTO(existingUser.get()), status);
		}
		else
		{
			status = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<>(status);
	}
}
