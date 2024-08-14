package com.social.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.social.commonutils.UserPostMapper;
import com.social.domain.UserPost;
import com.social.entity.ProfileE;
import com.social.entity.ProfileImageE;
import com.social.entity.UserPostE;
import com.social.repository.postgres.ProfileImageRepo;
import com.social.repository.postgres.UserPostRepo;
import com.social.repository.postgres.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService implements IPostService
{

	final UserPostRepo postRepo;

	final UserRepo userRepo;

	final ProfileImageRepo imageRepo;

	@Override
	public Optional<UserPost> addUserPost(UserPost profilePost)
	{
		UserPostE profilePostE = UserPostMapper.convertEntity(profilePost);

		Optional<ProfileE> profile = userRepo.findById(profilePost.getUserId());
		profilePostE.getImages().forEach(m -> {
			m.setUser(profile.get());
		});

		Optional<UserPost> result = Optional.empty();
		if (profile.isPresent())
		{
			profilePostE.setUser(profile.get());
			profilePostE = postRepo.save(profilePostE);
			result = Optional.of(UserPostMapper.convert(profilePostE));
		}
		return result;
	}

	@Override
	public List<UserPost> getAllUserPost(UserPost profilePost)
	{
		return null;
	}

	@Override
	public UserPost getUserPostById(UserPost profilePost)
	{
		return null;
	}

	@Override
	public Optional<UserPost> deleteUserPost(UserPost profilePost)
	{
		return Optional.empty();
	}

}
