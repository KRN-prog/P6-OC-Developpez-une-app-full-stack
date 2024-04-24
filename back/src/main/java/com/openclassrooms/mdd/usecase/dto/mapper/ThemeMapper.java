package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;
import com.openclassrooms.mdd.usecase.dto.request.ThemeRequestDto;

public class ThemeMapper {

    public static ThemeDto maptoThemesDto(ThemesEntity themesEntity) {
        return new ThemeDto(
                themesEntity.getId(),
                themesEntity.getTheme(),
                themesEntity.getTitle(),
                themesEntity.getContenu());
    }

    public static ThemesEntity maptoThemesEntity(ThemeRequestDto themeRequestDto) {
        return new ThemesEntity(
                themeRequestDto.getId(),
                themeRequestDto.getTheme(),
                themeRequestDto.getTitle(),
                themeRequestDto.getContenu());
    }

}
