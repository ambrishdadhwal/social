package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.social.commonutils.UserPostMapper;
import com.social.domain.UserPost;
import com.social.entity.ProfileE;
import com.social.entity.UserPostE;
import com.social.repository.UserPostRepo;
import com.social.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService
{

	final UserPostRepo postRepo;

	final UserRepo userRepo;

	@Override
	public Optional<UserPost> addUserPost(UserPost profilePost)
	{
		UserPostE profilePostE = UserPostMapper.convertEntity(profilePost);
		profilePostE.setCreatedTime(LocalDateTime.now());
		profilePostE.setModifiedTime(LocalDateTime.now());

		Optional<ProfileE> profile = userRepo.findById(profilePost.getUser().getId());
		Optional<UserPost> result = Optional.empty();
		if (profile.isPresent())
		{
			profilePostE.setProfile(profile.get());
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
