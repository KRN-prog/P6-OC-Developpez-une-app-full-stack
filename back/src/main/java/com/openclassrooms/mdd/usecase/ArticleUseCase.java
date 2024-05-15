package com.openclassrooms.mdd.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.service.ArticleService;
import com.openclassrooms.mdd.usecase.dto.ArticleDto;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleUseCase {

    private final ArticleService articleService;

    public ResponseEntity<?> getAllArticles() {
        return articleService.getAllArticles();
    }

    public ArticleDto getArticleById(Integer articleId) {
        ArticleDto articleDto = articleService.getArticleById(articleId);
        if (articleDto == null) {
            return null;
        }
        return articleDto;
    }

    public ResponseEntity<?> updateArticleById(ArticleRequestDto articleRequestDto, int articleId) {
        return articleService.updateArticleById(articleRequestDto, articleId);
    }

    public ArticleDto postNewArticle(ArticleRequestDto articleRequestDto) {
        return articleService.postNewArticle(articleRequestDto);
    }

}
