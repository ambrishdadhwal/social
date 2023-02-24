package com.social.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entity.SocialUserRoleE;

@Repository
public interface ISocialUserRoleRepo extends JpaRepository<SocialUserRoleE, Long>
{

}
