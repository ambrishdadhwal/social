package com.social.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "social_user_post")
@NamedQueries(
{@NamedQuery(query = "SELECT  u from UserPostE u where u.id =:postId", name = "UserPostE.findByPostId")})
@Slf4j
public class UserPostE
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_profile_id", referencedColumnName = "id")
	private ProfileE profile;

	@Column
	private String postData;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifiedTime;

	@PrePersist
	public void logNewUserAttempt()
	{
		log.info("Attempting to add new user with username: " + profile);
	}
}
