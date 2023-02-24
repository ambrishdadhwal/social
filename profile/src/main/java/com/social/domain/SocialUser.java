package com.social.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.social.common.Country;

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
public class SocialUser
{

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
	private Country country;
	private LocalDate dob;
	private Boolean isActive;
	private Set<String> roles;
	private LocalDateTime createDateTime;
	private LocalDateTime modifiedDateTime;

}
