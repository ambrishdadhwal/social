package com.social.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.entity.ProfileE;
import com.social.entity.ProfileImageE;
import com.social.repository.ProfileImageRepo;
import com.social.repository.ProfileRepo;
import com.social.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service("profileService")
@RequiredArgsConstructor
public class UserService implements IUserService
{

	private final UserRepo userRepo;

	private final ProfileRepo profileRepo;

	private final ProfileImageRepo userImageRepo;

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

	/*public boolean saveUserRole(SocialUser savedUser)
	{
		Long userId = savedUser.getId();
		
		savedUser.getRoles().forEach(n->{
			
			SocialUserRoleE userRole = SocialUserRoleE.builder()
				.fbuser(null)
				.role(n)
				.build();
				
		});
		
		return false;
	}*/

	// @Cacheable(value = "data", key = "'all-users'")
	public List<Profile> allUsers()
	{
		return userRepo.findAll().stream().map(ProfileMapper::convert).collect(Collectors.toList());
	}

	public Optional<Profile> getUserbyId(Long userId)
	{
		return allUsers().stream().filter(n -> n.getId().equals(userId)).findAny();
	}

	@Override
	public Optional<Profile> getUserbyUserNameAndId(String userName, Long userId)
	{
		return allUsers().stream()
			.filter(n -> n.getEmail().equals(userName) && n.getId().equals(userId))
			.findAny();
	}

	@Override
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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Profile> getUserbyEmail(String email)
	{
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Profile> getUserbyuserEmail(String email)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profile> searchUsers(String search)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
