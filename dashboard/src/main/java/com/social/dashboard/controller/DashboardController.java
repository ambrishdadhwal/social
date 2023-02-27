package com.social.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RequestMapping("/")
@RestController
public class DashboardController
{
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllPosts()
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{postId}", produces = "application/json")
    public ResponseEntity<?> getPost(@PathVariable(required = true) String postId)
    {
        return ResponseEntity.ok().build();
    }
}
