package com.social.user.controllers;

import java.util.List;
import java.util.Optional;

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

import com.social.commonutils.UserPostMapper;
import com.social.domain.UserPost;
import com.social.presentation.ProfileDTO;
import com.social.presentation.UserPostDTO;
import com.social.service.IPostService;
import com.social.service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class PostController
{

	final IUserService profileService;

	final IPostService postService;

	@GetMapping(value = "/{userId}/post")
	public ResponseEntity<List<UserPostDTO>> getAllPostsByUser(@PathVariable Long userId)
	{
		List<UserPost> userPosts = postService.getAllUserPost(null);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/post/{postId}")
	public ResponseEntity<UserPostDTO> getPostById(@PathVariable Long userId, @PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/post", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserPostDTO> addPostForUser(@PathVariable("userId") Long userId, @RequestBody @Validated UserPostDTO request)
	{
		request.setUser(ProfileDTO.builder().id(userId).build());
		Optional<UserPost> result = postService.addUserPost(UserPostMapper.convert(request));
		if (result.isPresent())
		{
			return new ResponseEntity<>(UserPostMapper.convert(result.get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping(value = "/{userId}/post/{postId}/")
	public ResponseEntity<List<UserPostDTO>> deletePost(@PathVariable Long userId, @PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
