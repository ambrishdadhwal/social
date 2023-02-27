package com.social.profile.controllers;

import java.util.List;

import com.social.profile.dto.ProfilePostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/post")
public class ProfilePostController
{

	@GetMapping(value = "/user/{userId}/")
	public ResponseEntity<List<ProfilePostDTO>> getAllPostsByUser(@PathVariable Long userId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/{postId}/")
	public ResponseEntity<ProfilePostDTO> getPostById(@PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/{postId}/user/{userId}/")
	public ResponseEntity<List<ProfilePostDTO>> getAllPosts(@PathVariable Long postId, @PathVariable Long userId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(value = "/{postId}/")
	public ResponseEntity<List<ProfilePostDTO>> addPostForUser(@Valid ProfilePostDTO request)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{postId}/")
	public ResponseEntity<List<ProfilePostDTO>> deletePost(@PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{postId}/user/{userId}/")
	public ResponseEntity<List<ProfilePostDTO>> deleteUserPost(@PathVariable Long postId, @PathVariable Long userId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
