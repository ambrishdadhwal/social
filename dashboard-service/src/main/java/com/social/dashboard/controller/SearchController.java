package com.social.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.service.IUserService;

@RestController
@RequestMapping("search")
public class SearchController
{

	@Autowired
	IUserService userService;

}
