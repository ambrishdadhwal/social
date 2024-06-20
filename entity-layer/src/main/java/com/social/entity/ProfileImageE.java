package com.social.entity;

import java.time.LocalDateTime;

import com.social.domain.ImageType;

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
	@JoinColumn(name = "social_user_id", referencedColumnName = "id")
	private ProfileE user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "social_post_id", referencedColumnName = "id")
	private UserPostE post;

	@Column
	private String imageName;

	@Column
	private String imageDescription;

	@Column
	private ImageType imageType;

	@Column
	private byte[] image;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDateTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifyDateTime;
}
