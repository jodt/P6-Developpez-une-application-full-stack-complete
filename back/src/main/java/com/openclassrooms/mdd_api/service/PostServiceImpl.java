package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.CreatePostDto;
import com.openclassrooms.mdd_api.dto.PostDto;
import com.openclassrooms.mdd_api.dto.PostWithCommentsDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.mapper.PostMapper;
import com.openclassrooms.mdd_api.model.Post;
import com.openclassrooms.mdd_api.model.Topic;
import com.openclassrooms.mdd_api.model.User;
import com.openclassrooms.mdd_api.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final TopicService topicService;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, UserService userService, TopicService topicService, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.topicService = topicService;
        this.postMapper = postMapper;
    }

    @Override
    public Optional<Post> findPostById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public PostWithCommentsDto findPostDtoById(Long postId) throws ResourceNotFoundException {
        Post post = this.findPostById(postId).orElseThrow(ResourceNotFoundException::new);
        return this.postMapper.asPostWithCommentDto(post);
    }

    /**
     * Retrieve all posts from topics the user is subscribed to
     *
     * @return list of postDto
     * @throws ResourceNotFoundException if user not found
     */
    @Override
    public List<PostDto> getPostsBySubscribedTopics() throws ResourceNotFoundException {

        log.info("Try to retrieve all posts from topics the user is subscribed to");

        User loggedUser = this.userService.getLoggedUser();

        List<Topic> subscribedTopics = loggedUser.getTopics();

        List <PostDto> posts =  this.postMapper.asPostDtos(this.postRepository.findByTopicInOrderByCreatedAtDesc(subscribedTopics));

        return posts;
    }


    /**
     * save a new post
     *
     * @param newPost new post to save
     * @throws ResourceNotFoundException if user not found
     */
    @Override
    public void createPost(CreatePostDto newPost) throws ResourceNotFoundException {
        User user = this.userService.getLoggedUser();
        Post post = this.postMapper.asPost(newPost, this.topicService);
        post.setAuthor(user);
        post.setCreatedAt(LocalDate.now());
        this.postRepository.save(post);
    }
}
