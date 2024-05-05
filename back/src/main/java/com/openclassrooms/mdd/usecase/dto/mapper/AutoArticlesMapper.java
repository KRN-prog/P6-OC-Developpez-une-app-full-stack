package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.usecase.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.models.ArticleEntity;

@Mapper
public interface AutoArticlesMapper {
    AutoArticlesMapper MAPPER = Mappers.getMapper(AutoArticlesMapper.class);

    ArticleDto mapArticleDto(ArticleEntity articleEntity);
}
