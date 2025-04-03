package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.model.Post;

import java.util.Optional;

public interface PostService {

    Optional<Post> findPostById(Long postId);

}
