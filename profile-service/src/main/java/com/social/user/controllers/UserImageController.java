package com.social.user.controllers;

import java.util.Optional;

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

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.domain.ProfileImage;
import com.social.presentation.ProfileDTO;
import com.social.service.IUserImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserImageController
{

	public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

	private final IUserImageService imageService;

	@GetMapping(value = "{userId}/image/{imageId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getImage(@PathVariable(required = true) String imageId, @PathVariable(required = true) String userId)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "{userId}/image/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getAllImages(@PathVariable(required = true) String userId)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/image", produces = "application/json")
	public ResponseEntity<ProfileDTO> uploadImage(@PathVariable("userId") Long userId, @RequestParam("file") MultipartFile file,
		@RequestParam("imageName") String imageName)
	{
		ProfileImage image = ProfileImage.builder().userId(userId).build();

		Optional<Profile> profile = imageService.uploadUserImage(image, file);

		if (profile.isPresent())
		{
			return new ResponseEntity<>(ProfileMapper.convertDTO(profile.get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping(value = "{userId}/image/{imageId}")
	public ResponseEntity<?> deleteImage(@PathVariable(required = true) String imageId, @PathVariable(required = true) String userId)
	{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}