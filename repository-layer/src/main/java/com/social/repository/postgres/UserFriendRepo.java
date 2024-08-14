package com.social.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entity.UserFriendE;

@Repository
public interface UserFriendRepo extends JpaRepository<UserFriendE, Long>
{

}
