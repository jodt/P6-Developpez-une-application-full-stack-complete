package com.openclassrooms.mdd_api.dto;

import com.openclassrooms.mdd_api.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    private String title;

    private String topic;

    private String author;

    private String content;

    private LocalDate createdAt;

}
