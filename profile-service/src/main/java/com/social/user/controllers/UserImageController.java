package com.social.user.controllers;

import com.social.domain.Profile;
import com.social.domain.ProfileImage;
import com.social.entity.ProfileImageE;
import com.social.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserImageController
{
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @Autowired(required = true)
    IUserService userService;

    @GetMapping(value = "{userId}/image/{imageId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getImage(@PathVariable(required = true) String imageId, @PathVariable(required = true) String userId)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "{userId}/image/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getAllImages(@PathVariable(required = true) String userId)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "{userId}/image/", produces = "application/json")
    public ResponseEntity<?> uploadImage(@PathVariable(required = true) Long userId,
              @RequestParam("image") MultipartFile file, @RequestParam("imageName") String imageName)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Profile> profile = userService.getUserbyUserNameAndId(authentication.getName(), userId);

        if(profile.isPresent())
        {
            try {
                StringBuilder fileNames = new StringBuilder();
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename());
                Files.write(fileNameAndPath, file.getBytes());
                profile.get().setProfileImage(fileNameAndPath.toString());

                ProfileImage profileImage = ProfileImage.builder()
                        .imageName(file.getOriginalFilename())
                        .imageDescription(fileNameAndPath.toString())
                       // .image(file.getBytes())
                        .build();

                Set<ProfileImage> existing = profile.get().getProfileImages();

                HashSet images = null;
                if(existing!=null)
                {
                    existing.add(profileImage);
                }
                else {
                    existing  = new HashSet();
                    existing.add(profileImage);
                }

                profile.get().setProfileImages(existing);

                userService.updateUser(profile.get());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(profile.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{userId}/image/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable(required = true) String imageId, @PathVariable(required = true) String userId)
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}