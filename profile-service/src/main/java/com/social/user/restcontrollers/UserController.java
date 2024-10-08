package com.social.user.restcontrollers;

import static com.social.user.utils.ProfileUtils.addLinkToUser;

import java.util.List;
import java.util.Optional;

import com.social.security.jwt.RequestContext;
import com.social.security.jwt.RequestContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.CommonResponse;
import com.social.presentation.ProfileDTO;
import com.social.presentation.ProfileUpdateDTO;
import com.social.service.IUserService;
import com.social.user.utils.ProfileUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{

	private final IUserService userService;

	private final RequestContextHolder requestContextHolder;

	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public CommonResponse<List<ProfileDTO>> getUsers(@RequestParam(name = "pageNumber") Integer pageNumber,
		@RequestParam(name = "pageSize") Integer pageSize)
	{
		List<Profile> users = userService.allUsersPaging(pageNumber, pageSize);
		List<ProfileDTO> response = users.stream().map(ProfileMapper::convertDTO).toList();
		response.forEach(ProfileUtils::addLinkToUser);
		CommonResponse<List<ProfileDTO>> dto = new CommonResponse<>();
		dto.setData(response);
		dto.setStatus(HttpStatus.OK);
		System.out.println("----->>>>>>" + requestContextHolder.getContext().getEmail());
		return dto;
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CommonResponse<ProfileDTO>> getUserById(@PathVariable(required = true, name = "id") Long id)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<Profile> profile = userService.getUserbyUserNameAndId(authentication.getName(), id);
		if (profile.isPresent())
		{
			CommonResponse<ProfileDTO> dto = new CommonResponse<>();
			dto.setData(ProfileMapper.convertDTO(profile.get()));
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Long> totalUsers()
	{
		return new ResponseEntity<>(userService.totalSocialUsers(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CommonResponse<ProfileDTO>> updateUser(@PathVariable("id") Long id, @RequestBody @Validated ProfileUpdateDTO user)
	{
		user.setId(id);
		Optional<Profile> newUser = userService.updateUser(ProfileMapper.convert(user));
		if (newUser.isPresent())
		{
			CommonResponse<ProfileDTO> dto = new CommonResponse<>();
			dto.setData(ProfileMapper.convertDTO(newUser.get()));
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CommonResponse<ProfileDTO>> createUser(@RequestBody @Validated ProfileDTO user) throws Exception
	{
		Optional<Profile> newUser = userService.saveUser(ProfileMapper.convert(user));
		if (newUser.isPresent())
		{
			ProfileDTO response = ProfileMapper.convertDTO(newUser.get());
			addLinkToUser(response);
			CommonResponse<ProfileDTO> dto = new CommonResponse<>();
			dto.setData(response);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProfileDTO> deleteUser(@PathVariable Long id)
	{
		userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
