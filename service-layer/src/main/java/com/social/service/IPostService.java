package com.social.service;

import java.util.List;
import java.util.Optional;

import com.social.domain.UserPost;

public interface IPostService
{

	public Optional<UserPost> addUserPost(UserPost profilePost);

	public List<UserPost> getAllUserPost(UserPost profilePost);

	public UserPost getUserPostById(UserPost profilePost);

	public Optional<UserPost> deleteUserPost(UserPost profilePost);
}
