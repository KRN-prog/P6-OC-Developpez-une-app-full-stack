package com.openclassrooms.mdd.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.usecase.ArticleUseCase;
import com.openclassrooms.mdd.usecase.dto.ArticleDto;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticle() {
        return this.articleUseCase.getAllArticles();
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticleById(@Valid @PathVariable("id") Integer id) {
        ArticleDto articleDto = this.articleUseCase.getArticleById(id);
        Map<String, String> response = new HashMap<>();

        if (articleDto == null) {
            response.put("error", "Article error: Cannot retrieve the good article.");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(articleDto);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<?> updateArticle(@Valid @RequestBody ArticleRequestDto articleRequestDto,
            @PathVariable("id") int articleId) {
        return this.articleUseCase.updateArticleById(articleRequestDto, articleId);
    }

    @PostMapping("/article")
    public ResponseEntity<?> postNewArticle(@Valid @RequestBody ArticleRequestDto articleRequestDto) {
        ArticleDto articleDto = this.articleUseCase.postNewArticle(articleRequestDto);
        if (articleDto != null) {
            return ResponseEntity.ok(articleDto);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot update the article");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
