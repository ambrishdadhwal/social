package com.social.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.dto.SocialUserPostDTO;

@RestController
@RequestMapping("/post")
public class SocialUserPostController
{

	@GetMapping(value = "/user/{userId}/")
	public ResponseEntity<List<SocialUserPostDTO>> getAllPostsByUser(@PathVariable Long userId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/{postId}/")
	public ResponseEntity<SocialUserPostDTO> getPostById(@PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/{postId}/user/{userId}/")
	public ResponseEntity<List<SocialUserPostDTO>> getAllPosts(@PathVariable Long postId, @PathVariable Long userId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(value = "/{postId}/")
	public ResponseEntity<List<SocialUserPostDTO>> addPostForUser(@Valid SocialUserPostDTO request)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{postId}/")
	public ResponseEntity<List<SocialUserPostDTO>> deletePost(@PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{postId}/user/{userId}/")
	public ResponseEntity<List<SocialUserPostDTO>> deleteUserPost(@PathVariable Long postId, @PathVariable Long userId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
