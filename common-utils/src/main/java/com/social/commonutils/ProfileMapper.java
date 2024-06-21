package com.social.commonutils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.social.domain.Country;
import com.social.domain.Gender;
import com.social.domain.ImageType;
import com.social.domain.Profile;
import com.social.domain.ProfileImage;
import com.social.entity.ProfileE;
import com.social.entity.ProfileImageE;
import com.social.entity.ProfileRoleE;
import com.social.presentation.CountryDTO;
import com.social.presentation.ImageTypeDTO;
import com.social.presentation.ProfileDTO;
import com.social.presentation.ProfileImageDTO;
import com.social.presentation.ProfileLoginDTO;
import com.social.presentation.ProfileUpdateDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfileMapper
{

	public static ProfileImageE convert(ProfileImage n)
	{
		if (n == null)
		{
			return null;
		}

		return ProfileImageE.builder()
			.id(n.getId())
			.user(ProfileMapper.convert(n.getProfile()))
			.imageName(n.getImageName())
			.imageDescription(n.getImageDescription())
			.imageType(n.getImageType())
			.image(n.getImage())
			.createDateTime(n.getCreateDateTime())
			.modifyDateTime(n.getModifyDateTime())
			.build();
	}

	public static ProfileImage convert(ProfileImageDTO n)
	{
		if (n == null)
		{
			return null;
		}

		return ProfileImage.builder()
			.id(n.getId())
			.imageName(n.getImageName())
			.imageDescription(n.getImageDescription())
			.image(n.getImage())
			.imageType(ImageType.valueOf(n.getImageType().toString()))
			.createDateTime(n.getCreateDate())
			.modifyDateTime(n.getModifyDate())
			.build();
	}

	public static ProfileImageDTO convertDTO(ProfileImage n)
	{
		if (n == null)
		{
			return null;
		}

		return ProfileImageDTO.builder()
			.id(n.getId())
			.imageName(n.getImageName())
			.imageDescription(n.getImageDescription())
			.imageType(ImageTypeDTO.valueOf(n.getImageType().toString()))
			.image(n.getImage())
			.createDate(n.getCreateDateTime())
			.modifyDate(n.getModifyDateTime())
			.build();
	}

	public static ProfileImage convert(ProfileImageE n)
	{
		if (n == null)
		{
			return null;
		}

		return ProfileImage.builder()
			.id(n.getId())
			.imageName(n.getImageName())
			.imageDescription(n.getImageDescription())
			.imageType(n.getImageType())
			.image(n.getImage())
			.createDateTime(n.getCreateDateTime())
			.modifyDateTime(n.getModifyDateTime())
			.build();
	}

	public static Profile convert(ProfileE from)
	{
		if (from == null)
		{
			return null;
		}

		Set<ProfileImage> images = null;

		if (from.getProfileImages() != null)
		{
			images = from.getProfileImages().stream().map(ProfileMapper::convert).collect(Collectors.toSet());
		}

		return Profile.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(from.getCountry())
			.dob(from.getDob())
			.profileImage(from.getProfileImage())
			.profileImages(images)
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

		Set<ProfileImageE> images = null;

		if (from.getProfileImages() != null)
		{
			images = from.getProfileImages().stream().map(ProfileMapper::convert).collect(Collectors.toSet());
		}

		return ProfileE.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(from.getCountry())
			.gender(Gender.NOT_INTERESTED_TO_MENTION)
			.dob(from.getDob())
			.userRoles(roles)
			.profileImage(from.getProfileImage())
			.profileImages(images)
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

		Set<ProfileImage> images = null;

		if (from.getProfileImages() != null)
		{
			images = from.getProfileImages().stream().map(ProfileMapper::convert).collect(Collectors.toSet());
		}

		return Profile.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(Country.getCountry(from.getCountry().getCountry()))
			.dob(from.getDob())
			.roles(roles)
			.profileImage(from.getProfileImage())
			.profileImages(images)
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
		Set<ProfileImageDTO> images = null;

		if (from.getProfileImages() != null)
		{
			images = from.getProfileImages().stream().map(ProfileMapper::convertDTO).collect(Collectors.toSet());
		}

		return ProfileDTO.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.email(from.getEmail())
			.password(from.getPassword())
			.country(CountryDTO.getCountry(from.getCountry().toString()))
			.dob(from.getDob())
			.roles(from.getRoles())
			.profileImage(from.getProfileImage())
			.profileImages(images)
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

	public static Profile convert(ProfileUpdateDTO from)
	{
		if (from == null)
		{
			return null;
		}

		return Profile.builder()
			.id(from.getId())
			.firstName(from.getFirstName())
			.lastName(from.getLastName())
			.country(Country.getCountry(from.getCountry().toString()))
			.dob(from.getDob())
			.build();
	}

}
