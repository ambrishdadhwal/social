package com.social.dashboard.controller;

import com.social.presentation.UserPostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController
{
    @GetMapping(value = "/")
    public ResponseEntity<List<UserPostDTO>> getAllPostsByUser(@RequestParam String param) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
