package com.social.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.social.domain.UserPost;
import com.social.entity.UserPostE;

@Repository("dashboardRepo")
public interface IDashboardRepo extends CrudRepository<UserPostE, Long>
{


}
