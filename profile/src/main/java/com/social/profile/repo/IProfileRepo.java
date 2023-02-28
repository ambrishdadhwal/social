package com.social.profile.repo;

import com.social.entity.ProfileE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepo extends JpaRepository<ProfileE, Long>
{

}
