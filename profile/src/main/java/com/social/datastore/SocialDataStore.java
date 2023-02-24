package com.social.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.social.dto.SocialUserDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SocialDataStore
{

	private static List<SocialUserDTO> users = new ArrayList<SocialUserDTO>();

	static long counter;

	public static Optional<SocialUserDTO> addUser(SocialUserDTO user)
	{
		user.setId(++SocialDataStore.counter);
		SocialDataStore.users.add(user);

		return Optional.of(user);
	}

	public static Optional<SocialUserDTO> getUser(Long id)
	{
		if (Objects.isNull(id))
		{
			return Optional.empty();
		}
		return SocialDataStore.users.stream().filter(n -> n.getId().equals(id)).findAny();
	}

	public static List<SocialUserDTO> getUsers()
	{
		return SocialDataStore.users;
	}

	public static Optional<SocialUserDTO> deleteUser(Long id)
	{
		if (Objects.isNull(id))
		{
			return Optional.empty();
		}

		Optional<SocialUserDTO> user = SocialDataStore.users.stream().filter(n -> n.getId().equals(id)).findAny();

		SocialDataStore.users.removeIf(n -> n.getId().equals(id));

		return user;
	}
}
