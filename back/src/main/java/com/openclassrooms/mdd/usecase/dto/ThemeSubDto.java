package com.openclassrooms.mdd.usecase.dto;

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
public class ThemeSubDto {

    private Integer id;
    private UserEntity user;
    private ThemesEntity theme;

}
