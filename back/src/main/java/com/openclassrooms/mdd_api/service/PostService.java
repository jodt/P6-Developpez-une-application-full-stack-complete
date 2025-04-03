package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.CreatePostDto;
import com.openclassrooms.mdd_api.dto.PostDto;
import com.openclassrooms.mdd_api.dto.TopicDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findPostById(Long postId);

   PostDto findPostDtoById(Long postId) throws ResourceNotFoundException;

    List<PostDto> getPostsBySubscribedTopics() throws ResourceNotFoundException;

    void createPost(CreatePostDto newPost) throws ResourceNotFoundException;

}
