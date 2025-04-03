package com.openclassrooms.mdd_api.dto;

import com.openclassrooms.mdd_api.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDto {

    private Long id;

    private String description;

    private String author;

    private String content;

    private LocalDate createdAt;

}
