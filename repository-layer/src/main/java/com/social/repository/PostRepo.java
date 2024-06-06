package com.social.repository;

import org.springframework.data.repository.CrudRepository;

import com.social.entity.UserPostE;

public interface PostRepo extends CrudRepository<UserPostE, Long>
{

}
