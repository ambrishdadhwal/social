package com.social.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.social.domain.ImageType;
import com.social.domain.Profile;
import com.social.domain.ProfileImage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserImageService implements IUserImageService
{

	private final IUserService userService;

	public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

	@Override
	public Optional<Profile> uploadUserImage(ProfileImage image, MultipartFile file)
	{
		Optional<Profile> profile = userService.getUserbyId(image.getUserId());

		if (profile.isPresent())
		{
			Profile existingProfile = profile.get();
			try
			{
				StringBuilder fileNames = new StringBuilder();
				Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
				fileNames.append(file.getOriginalFilename());
				Files.write(fileNameAndPath, file.getBytes());

				existingProfile.setProfileImage(fileNameAndPath.toString());

				ProfileImage profileImage = ProfileImage.builder()
					.imageName(file.getOriginalFilename())
					.imageDescription(fileNameAndPath.toString())
					.imageType(ImageType.PROFILE_PIC)
					.createDateTime(LocalDateTime.now())
					.modifyDateTime(LocalDateTime.now())
					.build();

				Set<ProfileImage> existing = existingProfile.getProfileImages();

				existing.add(profileImage);

				existingProfile.setProfileImages(existing);
				return userService.updateUser(existingProfile);
			}
			catch (Exception e)
			{
				log.error("Some thing went wrong while saving the image : {} ", e.getLocalizedMessage());
			}
		}
		return Optional.empty();
	}
}
