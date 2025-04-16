package com.openclassrooms.mdd_api.controller;

import com.openclassrooms.mdd_api.dto.CreatePostDto;
import com.openclassrooms.mdd_api.dto.PostDto;
import com.openclassrooms.mdd_api.dto.PostWithCommentsDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
        PostWithCommentsDto getPostById(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("start the process to retrieve post by its id");
        PostWithCommentsDto postDto = this.postService.findPostDtoById(id);
        log.info("Process terminated successfully");
        return postDto;
    }

    @GetMapping
    List<PostDto> getPostsBySubscribedTopics() throws ResourceNotFoundException {
        log.info("start the process to retrieve all posts from topics the user is subscribed to");
        List<PostDto> postDtos = postService.getPostsBySubscribedTopics();
        log.info("Process terminated successfully, {} posts retrieved", postDtos.size());
        return postDtos;
    }

    @PostMapping("/create")
    ResponseEntity<?> createPost(@Valid @RequestBody CreatePostDto newPost) throws ResourceNotFoundException {
        log.info("start the process to create a new post");
        this.postService.createPost(newPost);
        log.info("Process to create new post terminated successfully");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
