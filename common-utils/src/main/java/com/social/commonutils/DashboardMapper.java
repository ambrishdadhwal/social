package com.social.commonutils;

import com.social.domain.Profile;
import com.social.domain.UserPost;
import com.social.entity.UserPostE;
import com.social.presentation.UserPostDTO;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

@UtilityClass
public class DashboardMapper
{

	public static UserPost convert(UserPostDTO from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		return UserPost.builder()
			.user(Profile.builder().id(Long.valueOf(from.getUserId())).build())
			.post(from.getPost())
			.build();
	}

	public static UserPostDTO convert(UserPost from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		return UserPostDTO.builder()
			.userId(1)
			.post(from.getPost())
			.build();
	}

	public static UserPostE convertEntity(UserPost from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		return UserPostE.builder()
			.userId(from.getUser().getId())
			.postData(from.getPost())
			.build();
	}
	
	public static UserPost convert(UserPostE from)
	{
		if (!Objects.nonNull(from))
		{
			return null;
		}

		return UserPost.builder()
			.user(null)
			.post(from.getPostData())
			.build();
	}

}