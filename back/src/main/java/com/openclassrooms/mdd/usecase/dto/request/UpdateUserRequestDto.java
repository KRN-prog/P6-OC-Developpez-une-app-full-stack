package com.openclassrooms.mdd.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    private String newUsername;
    private String newEmail;

}
