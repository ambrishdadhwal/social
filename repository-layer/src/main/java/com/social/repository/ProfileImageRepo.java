package com.social.repository;

import com.social.entity.ProfileImageE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userImageRepo")
public interface ProfileImageRepo extends CrudRepository<ProfileImageE, Long> {

}
