package com.social.user.restcontrollers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
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
import org.springframework.web.bind.annotation.RestController;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.CommonResponse;
import com.social.presentation.ProfileDTO;
import com.social.presentation.ProfileLoginDTO;
import com.social.presentation.ProfileUpdateDTO;
import com.social.service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{

	private final IUserService userService;

	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public CommonResponse<List<ProfileDTO>> getUsers()
	{
		List<Profile> users = userService.allUsers();
		List<ProfileDTO> response = users.stream().map(ProfileMapper::convertDTO).toList();
		response.forEach(n -> {
			try
			{
				addLinkToUser(n);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		CommonResponse<List<ProfileDTO>> dto = new CommonResponse<>();
		// CollectionModel<ProfileDTO> collectionModel = CollectionModel.of(response);
		// collectionModel.add(linkTo(methodOn(ProfileDTO.class)).withSelfRel());
		dto.setData(response);
		dto.setStatus(HttpStatus.OK);
		return dto;
	}

	@PreAuthorize("hasAuthority('ROLE_USER1')")
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

	private void addLinkToUser(ProfileDTO user) throws Exception
	{
		user.add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
		user.add(linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
		user.add(linkTo(methodOn(UserLoginController.class).getUserToken(ProfileLoginDTO.builder().build())).withRel("login-token"));
	}

}
