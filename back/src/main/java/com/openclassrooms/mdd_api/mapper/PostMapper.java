package com.openclassrooms.mdd_api.mapper;

import com.openclassrooms.mdd_api.dto.PostDto;
import com.openclassrooms.mdd_api.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "author", expression = "java(post.getAuthor().getUserName())")
    PostDto asPostDto(Post post);

    List<PostDto> asPostDtos (List<Post> posts);
}
