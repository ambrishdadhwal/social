package com.social.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.social.common.Country;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "social_user", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@SuperBuilder
public class SocialUserE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private Boolean isActive;

	@Column
	@Enumerated(EnumType.STRING)
	private Country country;

	@Column
	private LocalDate dob;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_a_id", referencedColumnName = "id")
	private Set<SocialUserRoleE> userRoles;

	@Column
	private LocalDateTime createDateTime;

	@Column
	private LocalDateTime modifiedDateTime;
}
