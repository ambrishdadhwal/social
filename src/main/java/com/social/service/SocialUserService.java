package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.domain.SocialUser;
import com.social.entity.SocialUserE;
import com.social.entity.SocialUserRoleE;
import com.social.mapper.SocialUserMapper;
import com.social.repo.SocialUserRepo;
import com.social.repo.ISocialUserRepo;
import com.social.repo.ISocialUserRoleRepo;

@Service("userService")
public class SocialUserService implements ISocialUserService
{

	@Autowired
	ISocialUserRepo userRepo;

	@Autowired
	SocialUserRepo fUserRepo;
	
	@Autowired
	ISocialUserRoleRepo fUserRoleRepo;

	public Optional<SocialUser> saveUser(SocialUser user)
	{
		SocialUserE newUser = SocialUserMapper.convert(user);
		newUser.setCreateDateTime(LocalDateTime.now());
		newUser.setModifiedDateTime(LocalDateTime.now());
		newUser.setIsActive(true);
		SocialUserE savedUser = userRepo.save(newUser);

		return Optional.of(SocialUserMapper.convert(savedUser));
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

	public List<SocialUser> allUsers()
	{
		return userRepo.findAll().stream().map(SocialUserMapper::convert).collect(Collectors.toList());
	}

	public long totalSocialUsers()
	{
		return fUserRepo.totalUsers();
	}

	@Override
	public Optional<SocialUser> getUser(SocialUser user)
	{
		return fUserRepo.getUser(user);
	}

}
