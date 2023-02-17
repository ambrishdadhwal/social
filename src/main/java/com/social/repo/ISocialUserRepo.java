package com.social.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entity.SocialUserE;

@Repository
public interface ISocialUserRepo extends JpaRepository<SocialUserE, Long>
{

}
