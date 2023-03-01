package com.social.repository;

import com.social.entity.ProfileRoleE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRoleRepo extends JpaRepository<ProfileRoleE, Long>
{

}
