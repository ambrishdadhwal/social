package com.social.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.user.bootstrap.ProfileManager;
import com.social.user.config.properties.ConfigProperties;

@RestController
@RequestMapping("/misc")
public class MiscController
{

	@Autowired
	ProfileManager profileManager;

	@Autowired
	ConfigProperties configProperties;

	@Autowired
	Environment env;

	@GetMapping(value = "/test")
	public ResponseEntity<String> test()
	{
		return new ResponseEntity<>("Test API..!", HttpStatus.OK);
	}

	@GetMapping(value = "/profiles")
	public ResponseEntity<List<String>> getProfiles()
	{
		return new ResponseEntity<>(profileManager.getActiveProfiles(), HttpStatus.OK);
	}

	@GetMapping(value = "/config-props")
	public ResponseEntity<String> getCustomValues()
	{
		return new ResponseEntity<>("Properties are -----> " + configProperties.toString(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "/test1")
	public ResponseEntity<String> test1()
	{
		return new ResponseEntity<>("test1 is --", HttpStatus.OK);
	}

	@RequestMapping(value = "*", method = RequestMethod.GET)
	@ResponseBody
	public String getFallback()
	{
		return "Fallback for GET Requests";
	}

}
