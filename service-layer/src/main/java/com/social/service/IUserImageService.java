package com.social.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.social.domain.Profile;
import com.social.domain.ProfileImage;

public interface IUserImageService
{

	Optional<Profile> uploadUserImage(ProfileImage image, MultipartFile file) throws IOException;
}
