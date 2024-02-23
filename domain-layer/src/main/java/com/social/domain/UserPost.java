package com.social.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserPost
{

	private Integer id;
	private Profile user;
	private String post;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
