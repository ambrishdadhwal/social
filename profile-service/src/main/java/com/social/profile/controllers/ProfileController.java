package com.social.profile.controllers;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.CommonResponse;
import com.social.presentation.ProfileDTO;
import com.social.profile.datastore.ProfileDataStore;
import com.social.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CacheConfig(cacheManager = "cacheManager", cacheNames = "data")
public class ProfileController
{

	@Autowired(required = true)
	IProfileService profileService;

    @Cacheable(value = "data", key = "'all-users'")
	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	public CommonResponse<List<ProfileDTO>> getUsers()
	{
		List<Profile> users = profileService.allUsers();
        CommonResponse<List<ProfileDTO>> dto = new CommonResponse<List<ProfileDTO>>();
        dto.setData(users.stream().map(ProfileMapper::convertDTO).collect(Collectors.toList()));
		dto.setStatus(HttpStatus.OK);
		return dto;
	}

	@GetMapping(value = "/{id}")
	@Cacheable(value = "data", key = "'users' + '-' + #id")
	public CommonResponse<ProfileDTO> getUserById(@PathVariable(required = true) Long id)
	{
		//only login user can access their own data
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Optional<Profile> profile = profileService.getUserbyUserNameAndId(authentication.getName(), id);
		CommonResponse<ProfileDTO> dto = new CommonResponse<>();
		dto.setData(ProfileMapper.convertDTO(profile.get()));

		return dto;
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Long> totalUsers()
	{
		return new ResponseEntity<>(profileService.totalSocialUsers(), HttpStatus.OK);
	}

	@Cacheable(value = "data", key = "#user.email")
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CommonResponse> createUser(@RequestBody @Validated ProfileDTO user)
	{
		ProfileDataStore.addUser(user);
        Optional<Profile> newUser = profileService.saveUser(ProfileMapper.convert(user));
        CommonResponse<ProfileDTO> dto = new CommonResponse<>();
        dto.setData(ProfileMapper.convertDTO(newUser.get()));

		 return new ResponseEntity<>( dto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProfileDTO> deleteUser(@PathVariable Long id)
	{
		profileService.deleteUserById(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
