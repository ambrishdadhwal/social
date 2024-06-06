package com.social.commonutils;

import java.util.Objects;

import com.social.domain.Profile;
import com.social.domain.UserPost;
import com.social.entity.ProfileE;
import com.social.entity.UserPostE;
import com.social.presentation.ProfileDTO;
import com.social.presentation.UserPostDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserPostMapper
{

	public static UserPost convert(UserPostDTO from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		return UserPost.builder()
			.user(Profile.builder().id(from.getUser().getId()).build())
			.post(from.getPost())
			.build();
	}

	public static UserPostDTO convert(UserPost from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		Profile profile = from.getUser();

		return UserPostDTO.builder()
			.id(from.getId())
			.user(ProfileMapper.convertDTO(profile))
			.post(from.getPost())
			.createdTime(from.getCreatedTime())
			.modifiedTime(from.getModifiedTime())
			.build();
	}

	public static UserPostE convertEntity(UserPost from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		return UserPostE.builder()
			.postData(from.getPost())
			.build();
	}

	public static UserPost convert(UserPostE from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		ProfileE profile = from.getProfile();

		return UserPost.builder()
			.id(from.getId())
			.user(ProfileMapper.convert(profile))
			.post(from.getPostData())
			.createdTime(from.getCreatedTime())
			.modifiedTime(from.getModifiedTime())
			.build();
	}
}
