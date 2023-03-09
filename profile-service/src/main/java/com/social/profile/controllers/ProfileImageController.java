package com.social.profile.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class ProfileImageController
{
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

    @PostMapping(value = "{userId}/image/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> uploadImage(@PathVariable(required = true) String userId,
              @RequestParam("image") MultipartFile file, @RequestParam("imageName") String imageName)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{userId}/image/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable(required = true) String imageId, @PathVariable(required = true) String userId)
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

class UserImageDTO
{
    private Long id;
    private String imageName;
    private String imageDescription;
    private byte[] image;
}