package com.social.profile.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.social.domain.Country;
import com.social.domain.Gender;
import com.social.entity.ProfileE;
import com.social.entity.ProfileRoleE;
import com.social.repository.postgres.UserRepo;
import com.social.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{

	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private UserService userService;

	@Test
	public void addUser()
	{
		long k = 1;

		ProfileE user1 = ProfileE.builder()
			.id(Long.valueOf(k))
			.firstName("First Name - " + k)
			.lastName("Last Name - " + k)
			.email(k + "@social.com")
			.password("password-" + k)
			.isActive(true)
			.dob(LocalDate.now())
			.country(Country.INDIA)
			.gender(Gender.NOT_INTERESTED_TO_MENTION)
			.createDateTime(LocalDateTime.now())
			.modifiedDateTime(LocalDateTime.now())
			.build();
		ProfileRoleE role1 = ProfileRoleE.builder()
			.id(1L)
			.role("ADMIN_ROLE")
			.user(user1)
			.build();

		user1.setUserRoles(Set.of(role1));

		k = 2;
		ProfileE user2 = ProfileE.builder()
			.id(Long.valueOf(k))
			.firstName("First Name - " + k)
			.lastName("Last Name - " + k)
			.email(k + "@social.com")
			.password("password-" + k)
			.isActive(true)
			.dob(LocalDate.now())
			.country(Country.INDIA)
			.gender(Gender.NOT_INTERESTED_TO_MENTION)
			.createDateTime(LocalDateTime.now())
			.modifiedDateTime(LocalDateTime.now())
			.build();

		ProfileRoleE role2 = ProfileRoleE.builder()
			.id(2L)
			.role("USER_ROLE")
			.user(user2)
			.build();

		user2.setUserRoles(Set.of(role2));

		given(userRepo.findAll()).willReturn(List.of(user1, user2));

		var userList = userService.allUsers();

		assertThat(userList).isNotNull();
		assertThat(userList).size().isEqualTo(2);
	}

}
