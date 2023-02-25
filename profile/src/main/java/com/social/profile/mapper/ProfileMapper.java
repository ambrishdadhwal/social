package com.social.profile.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.social.profile.domain.Profile;
import com.social.profile.dto.ProfileLoginDTO;
import com.social.profile.dto.ProfileDTO;
import com.social.profile.entity.ProfileE;
import com.social.profile.entity.ProfileRoleE;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfileMapper
{

	public static Profile convert(ProfileE from)
	{
		if (from == null)
		{
			return null;
		}
		return Profile.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(from.getCountry())
			.dob(from.getDob())
			.roles(from.getUserRoles().stream().map(ProfileRoleE::getRole).collect(Collectors.toSet()))
			.build();
	}

	public static ProfileE convert(Profile from)
	{
		if (from == null)
		{
			return null;
		}

		Set<ProfileRoleE> roles = new HashSet<>();

		from.getRoles().forEach(n -> {
			roles.add(ProfileRoleE.builder().role(n).build());
		});

		return ProfileE.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(from.getCountry())
			.dob(from.getDob())
			.userRoles(roles)
			.build();
	}

	public static Profile convert(ProfileDTO from)
	{
		if (from == null)
		{
			return null;
		}

		Set<String> roles = new HashSet<>();
		roles.add("ADMIN");
		roles.add("USER");

		return Profile.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(from.getCountry())
			.dob(from.getDob())
			.roles(roles)
			.build();
	}

	public static ProfileDTO convertDTO(Profile from)
	{
		if (from == null)
		{
			return null;
		}
		return ProfileDTO.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(from.getCountry())
			.dob(from.getDob())
			.roles(from.getRoles())
			.build();
	}

	public static Profile convert(ProfileLoginDTO from)
	{
		if (from == null)
		{
			return null;
		}
		return Profile.builder()
			.email(from.getEmail())
			.userName(from.getUserName())
			.password(from.getPassword())
			.build();
	}

}
