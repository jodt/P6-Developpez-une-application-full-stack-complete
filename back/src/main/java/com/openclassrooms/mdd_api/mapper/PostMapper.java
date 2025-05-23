package com.openclassrooms.mdd_api.mapper;

import com.openclassrooms.mdd_api.dto.CreatePostDto;
import com.openclassrooms.mdd_api.dto.PostDto;
import com.openclassrooms.mdd_api.dto.PostWithCommentsDto;
import com.openclassrooms.mdd_api.model.Post;
import com.openclassrooms.mdd_api.service.TopicService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", imports = {TopicService.class}, uses = {CommentMapper.class})
public interface PostMapper {

    @Mappings({
            @Mapping(target = "author", expression = "java(post.getAuthor().getUserName())"),
            @Mapping(target = "topic", expression = "java(post.getTopic().getTitle())")
    })
    PostDto asPostDto(Post post);

    List<PostDto> asPostDtos(List<Post> posts);

    @Mappings({
            @Mapping(target = "author", expression = "java(post.getAuthor().getUserName())"),
            @Mapping(target = "topic", expression = "java(post.getTopic().getTitle())"),
    })
    PostWithCommentsDto asPostWithCommentDto(Post post);
    List<PostWithCommentsDto> asPostWithCommentsDtos(List<Post> posts);


    @Mappings({
            @Mapping(target = "topic", expression = "java(topicService.findTopicById(createPostDto.getTopicId()).orElse(null))"),
            @Mapping(target = "author", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "comments", ignore = true)
    })
    Post asPost(CreatePostDto createPostDto, TopicService topicService);
}
