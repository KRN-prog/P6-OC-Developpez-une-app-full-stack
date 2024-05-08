package com.openclassrooms.mdd.usecase.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;

@Mapper
public interface AutoThemesMapper {
    AutoThemesMapper MAPPER = Mappers.getMapper(AutoThemesMapper.class);

    ThemeDto mapThemeDto(ThemesEntity themesEntity);
}
