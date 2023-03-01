package com.social.profile.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import com.social.presentation.ProfileDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfileDataStore
{

	private static List<ProfileDTO> users = new ArrayList<ProfileDTO>();


	static long counter;

	public static Optional<ProfileDTO> addUser(ProfileDTO user)
	{
		user.setId(++ProfileDataStore.counter);
		ProfileDataStore.users.add(user);

		return Optional.of(user);
	}

	public static Optional<ProfileDTO> getUser(Long id)
	{
		if (Objects.isNull(id))
		{
			return Optional.empty();
		}
		return ProfileDataStore.users.stream().filter(n -> n.getId().equals(id)).findAny();
	}

	public static List<ProfileDTO> getUsers()
	{
		return ProfileDataStore.users;
	}

	public static Optional<ProfileDTO> deleteUser(Long id)
	{
		if (Objects.isNull(id))
		{
			return Optional.empty();
		}

		Optional<ProfileDTO> user = ProfileDataStore.users.stream().filter(n -> n.getId().equals(id)).findAny();

		ProfileDataStore.users.removeIf(n -> n.getId().equals(id));

		return user;
	}
}
