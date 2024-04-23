package com.openclassrooms.mdd.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.usecase.ArticleUseCase;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/mdd")
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    public ArticleController(ArticleUseCase articleUseCase) {
        this.articleUseCase = articleUseCase;
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticleById(@Valid @RequestBody Integer id) {
        return this.articleUseCase.getArticleById(id);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<?> updateArticle(@Valid @RequestBody ArticleRequestDto articleRequestDto, int articleId) {
        return this.articleUseCase.updateArticleById(articleRequestDto, articleId);
    }

    @PostMapping("/article")
    public Integer postNewArticle(@Valid @RequestBody Integer newArticleRequest) {
        return 1;
    }

}
