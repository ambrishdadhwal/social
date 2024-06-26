package com.social.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.presentation.UserPostDTO;
import com.social.service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class PostController
{

	IUserService profileService;

	@Value("${name:Config Server is not working. Please check...}")
	private String name;

	@GetMapping(value = "/{userId}/post")
	public ResponseEntity<List<UserPostDTO>> getAllPostsByUser(@PathVariable Long userId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/post/{postId}")
	public ResponseEntity<UserPostDTO> getPostById(@PathVariable Long userId, @PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}/post/{postId}/")
	public ResponseEntity<List<UserPostDTO>> deletePost(@PathVariable Long userId, @PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
