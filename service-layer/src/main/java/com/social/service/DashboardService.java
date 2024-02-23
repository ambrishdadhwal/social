package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.commonutils.DashboardMapper;
import com.social.domain.UserPost;
import com.social.entity.UserPostE;
import com.social.repository.IDashboardRepo;

@Service
public class DashboardService implements IDashboardService
{

	@Autowired
	IDashboardRepo dashboardRepo;

	public List<UserPost> addUserPost(UserPost profilePost)
	{
		UserPostE profilePostE = DashboardMapper.convertEntity(profilePost);
		profilePostE = dashboardRepo.save(profilePostE);

		return List.of(DashboardMapper.convert(profilePostE));
	}
}
