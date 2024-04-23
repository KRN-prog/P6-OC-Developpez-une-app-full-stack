package com.openclassrooms.mdd.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.service.ArticleService;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleUseCase {

    private final ArticleService articleService;

    public ResponseEntity<?> getArticleById(Integer articleId) {
        return articleService.getArticleById(articleId);
    }

    public ResponseEntity<?> updateArticleById(ArticleRequestDto articleRequestDto, int articleId) {
        return articleService.updateArticleById(articleRequestDto, articleId);
    }
    
}
