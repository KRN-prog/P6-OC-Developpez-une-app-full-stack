package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.usecase.dto.ArticleSaved;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

public class ArticleRequestMapper {

    public static ArticleEntity maptoArticle(ArticleRequestDto articleRequestDto) {
        return ArticleEntity.builder()
                .titre(articleRequestDto.getTitre())
                .date(articleRequestDto.getDate())
                .auteur(articleRequestDto.getAuteur())
                .content(articleRequestDto.getContent())
                .theme(articleRequestDto.getTheme())
                .build();
    }

    public static ArticleSaved maptoArticleSaved(ArticleEntity articleEntity) {
        return new ArticleSaved(
                articleEntity.getArticleId(),
                articleEntity.getTitre(),
                articleEntity.getDate(),
                articleEntity.getAuteur(),
                articleEntity.getContent(),
                Math.toIntExact(articleEntity.getTheme().getId()));
    }

}
