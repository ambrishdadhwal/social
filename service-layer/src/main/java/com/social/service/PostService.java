package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.social.repository.ProfileImageRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.social.commonutils.UserPostMapper;
import com.social.domain.UserPost;
import com.social.entity.ProfileE;
import com.social.entity.UserPostE;
import com.social.repository.UserPostRepo;
import com.social.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService implements IPostService {

    final UserPostRepo postRepo;

    final UserRepo userRepo;

    final ProfileImageRepo imageRepo;

    @Override
    public Optional<UserPost> addUserPost(UserPost profilePost) {
        UserPostE profilePostE = UserPostMapper.convertEntity(profilePost);
        profilePostE.setCreatedTime(LocalDateTime.now());
        profilePostE.setModifiedTime(LocalDateTime.now());

        Optional<ProfileE> profile = userRepo.findById(profilePost.getUserId());
        profilePostE.getImages().forEach(m -> {
            m.setUser(profile.get());
        });

        Optional<UserPost> result = Optional.empty();
        if (profile.isPresent()) {
            profilePostE.setUser(profile.get());
            log.info("Profile ID ---------- {}", profile.get().getId());
            profilePostE = postRepo.save(profilePostE);

            final UserPostE savedPost = profilePostE;
            profilePostE.getImages().forEach(m -> {
                m.setUser(profile.get());
                m.setPost(savedPost);
                imageRepo.save(m);
                log.info("Post successly save in Image entity :");
            });

            result = Optional.of(UserPostMapper.convert(profilePostE));
        }
        return result;
    }

    @Override
    public List<UserPost> getAllUserPost(UserPost profilePost) {
        return null;
    }

    @Override
    public UserPost getUserPostById(UserPost profilePost) {
        return null;
    }

    @Override
    public Optional<UserPost> deleteUserPost(UserPost profilePost) {
        return Optional.empty();
    }

}
