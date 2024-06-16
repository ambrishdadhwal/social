package com.social.user.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.social.domain.ProfileImage;
import com.social.presentation.ProfileImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.social.commonutils.UserPostMapper;
import com.social.domain.UserPost;
import com.social.presentation.ProfileDTO;
import com.social.presentation.UserPostDTO;
import com.social.service.IPostService;
import com.social.service.IUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserPostController {

    final IUserService profileService;

    final IPostService postService;

    public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping(value = "/{userId}/post")
    public ResponseEntity<List<UserPostDTO>> getAllPostsByUser(@PathVariable Long userId) {
        List<UserPost> userPosts = postService.getAllUserPost(null);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/post/{postId}")
    public ResponseEntity<UserPostDTO> getPostById(@PathVariable Long userId, @PathVariable Long postId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/post", produces = "application/json")
    public ResponseEntity<UserPostDTO> addPostForUser(@PathVariable("userId") Long userId,
                                                      @RequestParam(name = "post") String post,
                                                      @RequestParam(name = "file", required = false) MultipartFile file) {
        ProfileImageDTO postImage = null;
        if (file != null) {
            try {
                StringBuilder fileName = new StringBuilder();
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
                fileName.append(file.getOriginalFilename());
                Files.write(fileNameAndPath, file.getBytes());

                postImage = ProfileImageDTO.builder()
                        .imageName(file.getOriginalFilename())
                        .imageDescription(fileNameAndPath.toString())
                        .imageType("Post")
                        .createDate(LocalDateTime.now())
                        .modifyDate(LocalDateTime.now())
                        .build();
            } catch (Exception e) {
                log.error("Exception while saving image in local dir - {}", e.getLocalizedMessage());
            }
        }

        UserPostDTO request = UserPostDTO.builder()
                .userId(userId)
                .post(post)
                .images(postImage!=null ? Set.of(postImage) : null)
                .build();
        Optional<UserPost> result = postService.addUserPost(UserPostMapper.convert(request));
        if (result.isPresent()) {
            return new ResponseEntity<>(UserPostMapper.convert(result.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/{userId}/post/{postId}/")
    public ResponseEntity<List<UserPostDTO>> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
