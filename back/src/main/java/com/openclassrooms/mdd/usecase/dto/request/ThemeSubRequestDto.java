package com.openclassrooms.mdd.usecase.dto.request;

import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeSubRequestDto {

    private UserEntity user;
    private ThemesEntity theme;
}
