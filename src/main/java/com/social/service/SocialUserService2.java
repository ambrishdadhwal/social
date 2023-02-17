package com.social.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.domain.SocialUser;
import com.social.dto.SocialLoginDTO;
import com.social.entity.SocialUserE;
import com.social.mapper.SocialUserMapper;
import com.social.repo.SocialUserRepo;
import com.social.repo.ISocialUserRepo;

@Service("userService1")
public class SocialUserService2 implements ISocialUserService
{

	@Autowired
	ISocialUserRepo userRepo;

	@Autowired
	SocialUserRepo fUserRepo;

	public Optional<SocialUser> saveUser(SocialUser user)
	{
		SocialUserE savedUser = userRepo.save(SocialUserMapper.convert(user));

		return Optional.of(SocialUserMapper.convert(savedUser));
	}

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
		// TODO Auto-generated method stub
		return null;
	}

}
