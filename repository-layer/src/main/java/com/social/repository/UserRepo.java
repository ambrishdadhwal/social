package com.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.social.entity.ProfileE;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<ProfileE, Long>
{

	@Query(value = "SELECT * FROM social_user WHERE id = ?1", nativeQuery = true)
	Optional<ProfileE> findById(long userId);
}
