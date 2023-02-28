package com.social.profile.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.entity.ProfileE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.profile.repo.ProfileRepo;
import com.social.profile.repo.IProfileRepo;
import com.social.profile.repo.IProfileRoleRepo;

@Service("userService")
public class ProfileService implements IProfileService
{

	@Autowired
	IProfileRepo userRepo;

	@Autowired
	ProfileRepo fUserRepo;
	
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

	public long totalSocialUsers()
	{
		return fUserRepo.totalUsers();
	}

	@Override
	public Optional<Profile> getUser(Profile user)
	{
		return fUserRepo.getUser(user);
	}

}
