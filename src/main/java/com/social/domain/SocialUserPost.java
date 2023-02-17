package com.social.domain;

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
public class SocialUserPost
{

	private Long id;
	private SocialUser user;
	private String postData;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
