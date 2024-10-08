package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.social.commonutils.ProfileMapper;
import com.social.commonutils.SocialMethodVisit;
import com.social.domain.Profile;
import com.social.entity.ProfileE;
import com.social.repository.postgres.ProfileRepo;
import com.social.repository.postgres.UserRepo;

import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;

@Service("profileService")
@RequiredArgsConstructor
public class UserService implements IUserService
{

	private final UserRepo userRepo;

	private final ProfileRepo profileRepo;

	@SocialMethodVisit
	public Optional<Profile> saveUser(Profile user) throws Exception
	{
		Optional<ProfileE> existingUser = userRepo.findByEmail(user.getEmail());
		if (existingUser.isPresent())
		{
			throw new ProfileException("User already exist with email -- " + user.getEmail());
		}

		ProfileE newUser = ProfileMapper.convert(user);
		newUser.setCreateDateTime(LocalDateTime.now());
		newUser.setModifiedDateTime(LocalDateTime.now());
		newUser.setIsActive(true);
		try
		{
			ProfileE savedUser = userRepo.save(newUser);
			return Optional.of(ProfileMapper.convert(savedUser));
		}
		catch (Exception e)
		{
			throw new ProfileException(e.getLocalizedMessage());
		}
	}

	@SocialMethodVisit
	public List<Profile> allUsers()
	{
		return userRepo.findAll().stream().map(ProfileMapper::convert).collect(Collectors.toList());
	}
	
	@Override
	@SocialMethodVisit
	public List<Profile> allUsersPaging(Integer pageNumber, Integer pageSize)
	{
		Sort sortByName = Sort.by("firstName","lastName");
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByName);
		return userRepo.findAll(pageable).getContent().stream().map(ProfileMapper::convert).collect(Collectors.toList());
		//return userRepo.findAll().stream().map(ProfileMapper::convert).collect(Collectors.toList());
	}

	@SocialMethodVisit
	public Optional<Profile> getUserbyId(Long userId)
	{
		return allUsers().stream().filter(n -> n.getId().equals(userId)).findAny();
	}

	@Override
	@SocialMethodVisit
	public Optional<Profile> getUserbyUserNameAndId(@NotEmpty String userName, Long userId)
	{
		return allUsers().stream()
			.filter(n -> n.getEmail().equals(userName) && n.getId().equals(userId))
			.findAny();
	}

	@Override
	@SocialMethodVisit
	public Optional<Profile> deleteUserById(Long userId)
	{
		Optional<Profile> user = allUsers().stream().filter(n -> n.getId().equals(userId)).findAny();
		userRepo.deleteById(userId);
		return user;
	}

	public long totalSocialUsers()
	{
		return profileRepo.totalUsers();
	}

	@Override
	public Optional<Profile> getUser(Profile user) throws Exception
	{
		return profileRepo.getUser(user);
	}

	@Override
	@SocialMethodVisit
	public Optional<Profile> updateUser(Profile user)
	{
		Optional<ProfileE> existingDBUser = userRepo.findById(user.getId());

		if (existingDBUser.isPresent())
		{
			ProfileE profile = existingDBUser.get();
			profile.setFirstName(user.getFirstName());
			profile.setLastName(user.getLastName());
			profile.setCountry(user.getCountry());
			profile.setDob(user.getDob());
			profile.setModifiedDateTime(LocalDateTime.now());
			ProfileE savedUser = userRepo.save(profile);
			return Optional.of(ProfileMapper.convert(savedUser));
		}
		return Optional.empty();
	}

	@Override
	public Optional<Profile> getUserbyUserName(String userName)
	{
		return allUsers().stream().filter(n -> n.getUserName().equals(userName)).findAny();
	}

	@Override
	public Optional<Profile> getUserbyEmail(String email)
	{

		return Optional.empty();
	}

	@Override
	public List<Profile> getUserbyuserEmail(String email)
	{
		return allUsers().stream().filter(n -> n.getEmail().equals(email)).collect(Collectors.toList());
	}

	@Override
	public List<Profile> searchUsers(String search)
	{
		return getUsersSearchbyName(search);
	}

	@Override
	@SocialMethodVisit
	public List<Profile> getUsersSearchbyName(String name)
	{
		return userRepo.findByFirstNameContaining(name).stream().map(ProfileMapper::convert).collect(Collectors.toList());
	}

}
