package com.social.service;

public interface IUserFriendService
{

	public boolean updateFriendRequest(Long userId, Long friendId, boolean decision) throws ProfileException;
}
