package com.social.service;

import com.social.domain.UserPost;

import java.util.List;
import java.util.Optional;

public interface IDashboardService {

    public List<UserPost> addUserPost(UserPost profilePost);
}
