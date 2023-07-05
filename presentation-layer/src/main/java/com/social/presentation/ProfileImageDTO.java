package com.social.presentation;

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
public class ProfileImageDTO {

    private Long id;

    private ProfileDTO userId;

    private String imageName;

    private String imageDescription;

    private byte[] image;
}
