package com.social.repository;

import com.social.entity.ProfileE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<ProfileE, Long> {
}
