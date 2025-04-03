package com.openclassrooms.mdd_api.mapper;

import com.openclassrooms.mdd_api.dto.CommentDto;
import com.openclassrooms.mdd_api.model.Comment;
import com.openclassrooms.mdd_api.service.PostService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", imports = {PostService.class})
public interface CommentMapper {

    @Mappings({
            @Mapping(target = "post", expression = "java(postService.findPostById(commentDto.getPostId()).orElse(null))"),
            @Mapping(target = "author", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    Comment asComment(CommentDto commentDto, PostService postService);

    @Mappings({
            @Mapping(target = "author", expression = "java(comment.getAuthor() != null ? comment.getAuthor().getUserName() : null)"),
            @Mapping(target = "postId", ignore = true )
    })

    CommentDto asCommentDto(Comment comment);
    List<CommentDto> asCommentDtos (List<Comment> comments);

}
