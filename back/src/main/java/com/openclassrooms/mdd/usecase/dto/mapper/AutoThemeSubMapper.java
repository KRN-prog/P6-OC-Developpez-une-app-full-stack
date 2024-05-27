package com.openclassrooms.mdd.usecase.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.models.ThemeSubEntity;
import com.openclassrooms.mdd.usecase.dto.ThemeSubDto;

@Mapper
public interface AutoThemeSubMapper {

    AutoThemeSubMapper MAPPER = Mappers.getMapper(AutoThemeSubMapper.class);

    ThemeSubDto mapToThemeSubDto(ThemeSubEntity themeSubEntity);
}
