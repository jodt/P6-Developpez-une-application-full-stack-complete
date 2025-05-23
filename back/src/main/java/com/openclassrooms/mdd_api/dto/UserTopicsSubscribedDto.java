package com.openclassrooms.mdd_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTopicsSubscribedDto {
    Long id;

    private String title;

    private String description;

}
