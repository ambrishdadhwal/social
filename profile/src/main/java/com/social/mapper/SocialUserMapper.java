package com.social.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.social.domain.SocialUser;
import com.social.dto.SocialLoginDTO;
import com.social.dto.SocialUserDTO;
import com.social.entity.SocialUserE;
import com.social.entity.SocialUserRoleE;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SocialUserMapper
{

	public static SocialUser convert(SocialUserE from)
	{
		if (from == null)
		{
			return null;
		}
		return SocialUser.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(from.getCountry())
			.dob(from.getDob())
			.roles(from.getUserRoles().stream().map(SocialUserRoleE::getRole).collect(Collectors.toSet()))
			.build();
	}

	public static SocialUserE convert(SocialUser from)
	{
		if (from == null)
		{
			return null;
		}

		Set<SocialUserRoleE> roles = new HashSet<>();

		from.getRoles().forEach(n -> {
			roles.add(SocialUserRoleE.builder().role(n).build());
		});

		return SocialUserE.builder()
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

	public static SocialUser convert(SocialUserDTO from)
	{
		if (from == null)
		{
			return null;
		}

		Set<String> roles = new HashSet<>();
		roles.add("ADMIN");
		roles.add("USER");

		return SocialUser.builder()
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

	public static SocialUserDTO convertDTO(SocialUser from)
	{
		if (from == null)
		{
			return null;
		}
		return SocialUserDTO.builder()
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

	public static SocialUser convert(SocialLoginDTO from)
	{
		if (from == null)
		{
			return null;
		}
		return SocialUser.builder()
			.email(from.getEmail())
			.userName(from.getUserName())
			.password(from.getPassword())
			.build();
	}

}
