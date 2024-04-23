package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.usecase.dto.ArticleDto;

public class ArticleMapper {
    
    public static ArticleDto maptoArticleDto(ArticleEntity articleEntity) {
        return new ArticleDto(
                articleEntity.getId(),
                articleEntity.getTitre(),
                articleEntity.getDate(),
                articleEntity.getAuteur(),
                articleEntity.getContent());
    }

    public static ArticleEntity maptoArticle(ArticleDto articleDto) {
        return new ArticleEntity(
                articleDto.getId(),
                articleDto.getTitre(),
                articleDto.getDate(),
                articleDto.getAuteur(),
                articleDto.getContent());
    }

}
