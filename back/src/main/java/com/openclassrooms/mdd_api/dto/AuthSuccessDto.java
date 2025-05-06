package com.openclassrooms.mdd_api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthSuccessDto {

    private String token;

}
