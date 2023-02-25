package com.social.profile.repo;

import com.social.profile.entity.ProfileRoleE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRoleRepo extends JpaRepository<ProfileRoleE, Long>
{

}
