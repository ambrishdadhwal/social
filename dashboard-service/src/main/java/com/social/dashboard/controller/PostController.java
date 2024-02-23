package com.social.dashboard.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.social.commonutils.DashboardMapper;
import com.social.domain.UserPost;
import com.social.presentation.UserPostDTO;
import com.social.service.IDashboardService;
import com.social.service.IUserService;

@RestController
@RequestMapping("/user")
public class PostController
{

	@Autowired
	IUserService profileService;

	@Autowired
	IDashboardService dashboardService;

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

	@PostMapping(value = "/{userId}/post", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<UserPostDTO>> addPostForUser(@PathVariable("userId") Long userId, @RequestBody @Validated UserPostDTO request)
	{
		List<UserPost> result = dashboardService.addUserPost(DashboardMapper.convert(request));

		return new ResponseEntity<>(result.stream().map(DashboardMapper::convert).collect(Collectors.toUnmodifiableList()), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}/post/{postId}/")
	public ResponseEntity<List<UserPostDTO>> deletePost(@PathVariable Long userId, @PathVariable Long postId)
	{
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
