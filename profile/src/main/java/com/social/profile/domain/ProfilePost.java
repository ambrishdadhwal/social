package com.social.profile.domain;

import java.time.LocalDateTime;

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
public class ProfilePost
{

	private Long id;
	private Profile user;
	private String postData;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
