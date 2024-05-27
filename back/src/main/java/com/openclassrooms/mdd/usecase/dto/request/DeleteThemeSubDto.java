package com.openclassrooms.mdd.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteThemeSubDto {
    private Integer themeId;
    private Integer userId;
}
