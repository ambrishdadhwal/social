package com.social.dashboard.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.CommonResponse;
import com.social.presentation.ProfileDTO;
import com.social.service.IUserService;

@RestController
@RequestMapping("search")
public class SearchController
{
	
	@Autowired(required = true)
	IUserService userService;

	/*
	 * search for users by email, first name, last name
	 * */
	@GetMapping(value = "/{search}", consumes = "application/json", produces = "application/json")
	public CommonResponse<List<ProfileDTO>> searchUsers(@PathVariable(required = true) String search)
	{
		List<Profile> users = userService.searchUsers(search);
		CommonResponse<List<ProfileDTO>> dto = new CommonResponse<List<ProfileDTO>>();
		dto.setData(users.stream().map(ProfileMapper::convertDTO).collect(Collectors.toList()));
		dto.setStatus(HttpStatus.OK);
		return dto;
	}

}
