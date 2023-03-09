package com.social.profile.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserFriendController
{
    @GetMapping(value = "{userId}/friend/{friendId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getFriend(@PathVariable(required = true) String userId, @PathVariable(required = true) String friendId)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "{userId}/friend/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getAllFriends(@PathVariable(required = true) String userId)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
