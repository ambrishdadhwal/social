package com.social.commonutils;

import com.social.domain.Country;
import com.social.domain.Profile;
import com.social.entity.ProfileE;
import com.social.entity.ProfileRoleE;
import com.social.presentation.CountryDTO;
import com.social.presentation.ProfileDTO;
import com.social.presentation.ProfileLoginDTO;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
			.createDateTime(from.getCreateDateTime())
			.modifiedDateTime(from.getModifiedDateTime())
			.isActive(from.getIsActive())
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
			.createDateTime(from.getCreateDateTime())
			.modifiedDateTime(from.getModifiedDateTime())
			.isActive(from.getIsActive())
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
			.country(Country.getCountry(from.getCountry().getCountry()))
			.dob(from.getDob())
			.roles(roles)
			.createDateTime(from.getCreateDateTime())
			.modifiedDateTime(from.getModifyDateTime())
			.isActive(from.getIsActive())
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
			.country(CountryDTO.getCountry(from.getCountry().getCountry()))
			.dob(from.getDob())
			.roles(from.getRoles())
			.createDateTime(from.getCreateDateTime())
			.modifyDateTime(from.getModifiedDateTime())
			.isActive(from.getIsActive())
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
