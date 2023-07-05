package com.social.entity;

import com.social.domain.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "social_user", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@SuperBuilder
public class ProfileE {

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
	private String profileImage;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_a_id", referencedColumnName = "id")
	private Set<ProfileImageE> profileImages;

	@Column
	private Boolean isActive;

	@Column
	@Enumerated(EnumType.STRING)
	private Country country;

	@Column
	private LocalDate dob;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_a_id", referencedColumnName = "id")
	private Set<ProfileRoleE> userRoles;

	@Column
	private LocalDateTime createDateTime;

	@Column
	private LocalDateTime modifiedDateTime;
}
