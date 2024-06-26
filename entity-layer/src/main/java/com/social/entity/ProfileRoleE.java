package com.social.entity;

import jakarta.persistence.*;
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
@Table(name = "social_user_role")
@SuperBuilder
public class ProfileRoleE
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//after
//	@JoinColumn(name = "user_a_id", referencedColumnName = "id")
	
	//before
	@JoinColumn(name = "user_a_id")
	@ManyToOne
	private ProfileE user;

	@Column
	private String role;
}
