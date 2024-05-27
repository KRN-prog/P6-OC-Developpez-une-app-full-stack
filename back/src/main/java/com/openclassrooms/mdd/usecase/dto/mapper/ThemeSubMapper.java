package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.ThemeSubEntity;
import com.openclassrooms.mdd.usecase.dto.ThemeSubDto;
import com.openclassrooms.mdd.usecase.dto.request.ThemeSubRequestDto;

public class ThemeSubMapper {
    public static ThemeSubEntity maptoThemesSub(ThemeSubRequestDto themeSubRequestDto) {
        return ThemeSubEntity.builder()
                .user(themeSubRequestDto.getUser())
                .theme(themeSubRequestDto.getTheme())
                .build();
    }

    public static ThemeSubEntity mapToThemesSubEntity(ThemeSubDto themeSubDto) {
        return new ThemeSubEntity(
                themeSubDto.getId(),
                themeSubDto.getUser(),
                themeSubDto.getTheme());
    }

    public static ThemeSubDto mapToThemesSubDto(ThemeSubEntity themesEntity) {
        return new ThemeSubDto(
                themesEntity.getIdSub(),
                themesEntity.getUser(),
                themesEntity.getTheme());
    }
}
