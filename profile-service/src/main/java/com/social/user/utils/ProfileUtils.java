package com.social.user.utils;

import com.social.domain.ProfileImage;
import com.social.presentation.ImageTypeDTO;
import com.social.presentation.ProfileImageDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
@Slf4j
public class ProfileUtils<T>
{

	public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

	public static Set<ProfileImageDTO> createImages(MultipartFile[] files)
	{
		Set<ProfileImageDTO> images = new HashSet<>();
		if (files != null)
		{
			try
			{
				for (MultipartFile file : files)
				{
					StringBuilder fileName = new StringBuilder();
					Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
					fileName.append(file.getOriginalFilename());
					Files.write(fileNameAndPath, file.getBytes());

					ProfileImageDTO postImage = ProfileImageDTO.builder()
						.imageName(file.getOriginalFilename())
						.imageDescription(fileNameAndPath.toString())
						.imageType(ImageTypeDTO.POST_PIC)
						.createDate(LocalDateTime.now())
						.modifyDate(LocalDateTime.now())
						.build();
					images.add(postImage);
				}
			}
			catch (Exception e)
			{
				log.error("Exception while saving image in local dir - {}", e.getLocalizedMessage());
			}
		}
		return images;
	}
}
