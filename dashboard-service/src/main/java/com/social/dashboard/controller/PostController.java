package com.social.dashboard.controller;

import com.social.presentation.ProfilePostDTO;
import com.social.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RefreshScope
public class PostController {

    @Autowired
    ProfileService profileService;

    @Value("${name:Config Server is not working. Please check...}")
    private String name;

    @GetMapping(value = "/user/{userId}/")
    public ResponseEntity<List<ProfilePostDTO>> getAllPostsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}/")
    public ResponseEntity<ProfilePostDTO> getPostById(@PathVariable Long postId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}/user/{userId}/")
    public ResponseEntity<List<ProfilePostDTO>> getAllPosts(@PathVariable Long postId, @PathVariable Long userId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/{postId}/")
    public ResponseEntity<List<ProfilePostDTO>> addPostForUser(@Valid ProfilePostDTO request) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{postId}/")
    public ResponseEntity<List<ProfilePostDTO>> deletePost(@PathVariable Long postId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{postId}/user/{userId}/")
    public ResponseEntity<List<ProfilePostDTO>> deleteUserPost(@PathVariable Long postId, @PathVariable Long userId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/msg/")
    public String getMsg() {
        return name;
    }

}
