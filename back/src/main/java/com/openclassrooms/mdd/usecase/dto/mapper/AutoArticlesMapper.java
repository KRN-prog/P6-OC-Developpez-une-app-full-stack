package com.openclassrooms.mdd.usecase.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleDto;

@Mapper
public interface AutoArticlesMapper {
    AutoArticlesMapper MAPPER = Mappers.getMapper(AutoArticlesMapper.class);

    ArticleDto mapArticleDto(ArticleEntity articleEntity);
}
