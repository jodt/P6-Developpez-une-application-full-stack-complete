package com.openclassrooms.mdd_api.mapper;

import com.openclassrooms.mdd_api.dto.CommentDto;
import com.openclassrooms.mdd_api.model.Comment;
import com.openclassrooms.mdd_api.model.User;
import com.openclassrooms.mdd_api.service.PostService;
import com.openclassrooms.mdd_api.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", imports = {PostService.class})
public interface CommentMapper {

    @Mapping(target = "post", expression = "java(postService.findPostById(commentDto.getPostId()).orElse(null))")
    @Mapping(target = "author", ignore = true )
    Comment asComment(CommentDto commentDto, PostService postService);

    @Mapping(target = "author", expression = "java(comment.getAuthor() != null ? comment.getAuthor().getUserName() : null)")
    CommentDto asCommentDto(Comment comment);
    List<CommentDto> asCommentDtos (List<Comment> comments);

}
