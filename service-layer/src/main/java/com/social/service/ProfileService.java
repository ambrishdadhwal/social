package com.social.service;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.entity.ProfileE;
import com.social.repository.IProfileRoleRepo;
import com.social.repository.ProfileRepo;
import com.social.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("profileService")
public class ProfileService implements IProfileService
{

	@Autowired
	UserRepo userRepo;

	@Autowired
	ProfileRepo profileRepo;
	
	@Autowired
	IProfileRoleRepo fUserRoleRepo;

	public Optional<Profile> saveUser(Profile user)
	{
		ProfileE newUser = ProfileMapper.convert(user);
		newUser.setCreateDateTime(LocalDateTime.now());
		newUser.setModifiedDateTime(LocalDateTime.now());
		newUser.setIsActive(true);
		ProfileE savedUser = userRepo.save(newUser);

		return Optional.of(ProfileMapper.convert(savedUser));
	}

	@Override
	public void recover(SQLException e, String sql) {

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

	public List<Profile> allUsers()
	{
		return userRepo.findAll().stream().map(ProfileMapper::convert).collect(Collectors.toList());
	}

	public Optional<Profile> getUserbyId(Long userId)
	{
		return allUsers().stream().filter(n->n.getId().equals(userId)).findAny();
	}

	@Override
	public Optional<Profile> getUserbyUserNameAndId(String userName, Long userId) {
		return allUsers().stream()
				.filter(n->n.getEmail().equals(userName) && n.getId().equals(userId))
				.findAny();
	}

	public long totalSocialUsers()
	{
		return profileRepo.totalUsers();
	}

	@Override
	public Optional<Profile> getUser(Profile user)
	{
		return profileRepo.getUser(user);
	}

}
