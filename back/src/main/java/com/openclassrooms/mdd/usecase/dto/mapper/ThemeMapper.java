package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;

public class ThemeMapper {

    public static ThemeDto maptoThemesDto(ThemesEntity themesEntity) {
        return new ThemeDto(
                themesEntity.getId(),
                themesEntity.getTheme(),
                themesEntity.getTitle(),
                themesEntity.getContenu());
    }

}
