package com.social.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

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
public class SocialUserPostDTO
{

	private Long id;

	@NotNull
	private Long userId;

	@NotNull
	private String postData;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
