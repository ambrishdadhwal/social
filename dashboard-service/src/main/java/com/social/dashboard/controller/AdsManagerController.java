package com.social.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.service.IUserService;

@RestController
@RequestMapping("/ads")
public class AdsManagerController
{
	@Autowired
	IUserService userService;
	
	/*
	 * get all ads
	 * */
	@GetMapping(value = "/")
	public ResponseEntity<?> getAllAds()
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
