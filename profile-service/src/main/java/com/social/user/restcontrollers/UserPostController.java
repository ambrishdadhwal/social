package com.social.user.restcontrollers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.social.commonutils.UserPostMapper;
import com.social.domain.Profile;
import com.social.domain.UserPost;
import com.social.presentation.ProfileImageDTO;
import com.social.presentation.UserPostDTO;
import com.social.service.IPostService;
import com.social.service.IUserService;
import com.social.service.ProfileException;
import com.social.user.utils.ProfileUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserPostController
{

	final IUserService profileService;

	final IPostService postService;

	public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

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

	@PostMapping(value = "/{userId}/post", produces = "application/json")
	public ResponseEntity<UserPostDTO> addPostForUser(@PathVariable("userId") Long userId,
		@RequestParam(name = "post") String post,
		@RequestParam(name = "file", required = false) MultipartFile[] files, HttpServletRequest httpRequest) throws ProfileException, IOException
	{
		Profile profile = (Profile)httpRequest.getAttribute("CurrentUser");
		if(!userId.equals(profile.getId()))
		{
			throw new ProfileException("You can only create post with Logged In User");
		}
		Set<ProfileImageDTO> images = ProfileUtils.createImages(files);
		UserPostDTO request = UserPostDTO.builder()
			.userId(userId)
			.post(post)
			.images(images)
			.build();
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
