package com.social.entity;

import java.time.LocalDateTime;

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
@Table(name = "social_user_image")
@SuperBuilder
public class ProfileImageE
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_a_id", referencedColumnName = "id")
	private ProfileE user;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = UserPostE.class)
	@JoinColumn(name = "post_id")
	private UserPostE post;

	@Column
	private String imageName;

	@Column
	private String imageDescription;

	@Column
	private String imageType;

	@Column
	private byte[] image;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDateTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifyDateTime;
}
