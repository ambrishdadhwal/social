package com.social.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProfileImage {

    private Long id;

    private Profile userId;

    private String imageName;

    private String imageDescription;

    private byte[] image;
}
