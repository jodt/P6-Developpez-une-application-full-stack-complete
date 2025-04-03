package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.CommentDto;
import com.openclassrooms.mdd_api.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(CommentDto commentDto, String userName);

    List<CommentDto> findCommentsByPostId(Long id);
}
