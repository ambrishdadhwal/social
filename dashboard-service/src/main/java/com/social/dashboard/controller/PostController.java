package com.social.dashboard.controller;

import com.social.presentation.ProfilePostDTO;
import com.social.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RefreshScope
public class PostController {

    @Autowired
    ProfileService profileService;

    @Value("${name:Config Server is not working. Please check...}")
    private String name;

    @GetMapping(value = "/{userId}/post")
    public ResponseEntity<List<ProfilePostDTO>> getAllPostsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/post/{postId}")
    public ResponseEntity<ProfilePostDTO> getPostById(@PathVariable Long userId, @PathVariable Long postId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/post")
    public ResponseEntity<List<ProfilePostDTO>> addPostForUser(@PathVariable Long userId, @Valid ProfilePostDTO request) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}/post/{postId}/")
    public ResponseEntity<List<ProfilePostDTO>> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/msg/")
    public String getMsg() {
        return name;
    }

}
