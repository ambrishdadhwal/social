package com.social.profile.service;

import java.sql.SQLException;
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

@Service("userService1")
public class ProfileService2 implements IProfileService
{

	@Autowired
	IProfileRepo userRepo;

	@Autowired
	ProfileRepo fUserRepo;

	public Optional<Profile> saveUser(Profile user)
	{
		ProfileE savedUser = userRepo.save(ProfileMapper.convert(user));

		return Optional.of(ProfileMapper.convert(savedUser));
	}

	@Override
	public void recover(SQLException e, String sql)
	{
		//alternate SQL queries to insert data in batch tables to insert to main table later.
	}

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
		// TODO Auto-generated method stub
		return null;
	}

}
