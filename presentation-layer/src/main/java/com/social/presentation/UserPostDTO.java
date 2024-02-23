package com.social.presentation;

import jakarta.validation.constraints.NotNull;
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
public class UserPostDTO
{

	private Long id;

	@NotNull
	private Integer userId;

	//@NotNull
	private String post;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
