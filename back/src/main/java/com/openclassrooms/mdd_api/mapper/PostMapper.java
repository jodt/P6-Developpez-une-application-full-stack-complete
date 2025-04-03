package com.openclassrooms.mdd_api.mapper;

import com.openclassrooms.mdd_api.dto.PostDto;
import com.openclassrooms.mdd_api.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mappings({
            @Mapping(target = "author", expression = "java(post.getAuthor().getUserName())"),
            @Mapping(target = "topic", expression = "java(post.getTopic().getTitle())")
    })

    PostDto asPostDto(Post post);

    List<PostDto> asPostDtos (List<Post> posts);
}
