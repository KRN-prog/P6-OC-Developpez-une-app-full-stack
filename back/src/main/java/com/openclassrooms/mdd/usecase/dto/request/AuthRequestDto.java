package com.openclassrooms.mdd.usecase.dto.request;

import lombok.Getter;

@Getter
public class AuthRequestDto {
    private String emailOrUsername;
    private String password;
}
