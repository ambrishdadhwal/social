package com.social.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.social.entity.UserFriendE;
import com.social.repository.UserFriendRepo;
import com.social.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFriendService implements IUserFriendService
{

	private final UserFriendRepo friendRepo;

	private final UserRepo userRepo;

	@Override
	public boolean updateFriendRequest(Long userId, Long friendId, boolean decision) throws ProfileException
	{
		var currentUser = userRepo.findById(userId);
		var friend = userRepo.findById(friendId);
		if (currentUser.isPresent() && friend.isPresent())
		{
			UserFriendE entity = UserFriendE.builder()
				.user(currentUser.get())
				.friend(friend.get())
				.isFriend(decision)
				.requestAccepted(decision)
				.timestamp(LocalDateTime.now())
				.build();

			friendRepo.save(entity);
			return true;
		}
		else
		{
			if (!currentUser.isPresent())
			{
				throw new ProfileException("LoggedIn user don't exist!");
			}
			if (!friend.isPresent())
			{
				throw new ProfileException("Friend you are looking for don't exist!");
			}
		}
		return false;
	}

}
