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
public class ProfilePostDTO
{

	private Long id;

	@NotNull
	private Long userId;

	@NotNull
	private String postData;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
