package com.social.service;

import java.util.List;
import java.util.Optional;

import com.social.domain.SocialUser;

public interface ISocialUserService
{

	public Optional<SocialUser> saveUser(SocialUser user);

	public List<SocialUser> allUsers();

	public long totalSocialUsers();

	public Optional<SocialUser> getUser(SocialUser user);

}
