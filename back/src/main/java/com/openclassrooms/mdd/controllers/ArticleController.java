package com.openclassrooms.mdd.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.usecase.ArticleUseCase;
import com.openclassrooms.mdd.usecase.dto.ArticleDto;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Class permettant d'intéragir avec les articles de l'application
 */
@RestController
@RequestMapping("/mdd")
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    public ArticleController(ArticleUseCase articleUseCase) {
        this.articleUseCase = articleUseCase;
    }

    /**
     * Controller pour pouvoir récupérer l'entièreté des articles
     * 
     * @return Une liste d'articles
     */
    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticle() {
        List<ArticleDto> articleDtos = this.articleUseCase.getAllArticles();
        Map<String, String> response = new HashMap<>();
        if (articleDtos == null) {
            response.put("error", "Article error: Cannot retrieve articles.");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(articleDtos);
    }

    /**
     * Controller pour récupérer l'article via un ID
     * 
     * @param id
     * @return 1 article choisie par l'id
     */
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

    /**
     * Modifier un article par son id
     * 
     * @param articleRequestDto
     * @param articleId
     * @return 1 article modifier par son id et les modification apportés
     */
    @PutMapping("/article/{id}")
    public ResponseEntity<?> updateArticle(@Valid @RequestBody ArticleRequestDto articleRequestDto,
            @PathVariable("id") int articleId) {
        return this.articleUseCase.updateArticleById(articleRequestDto, articleId);
    }

    /**
     * Ajouter un article
     * 
     * @param articleRequestDto
     * @return 1 article ajouter avec l'articleRequestDto
     */
    @PostMapping("/article")
    public ResponseEntity<?> postNewArticle(@Valid @RequestBody ArticleRequestDto articleRequestDto) {
        ArticleDto articleDto = this.articleUseCase.postNewArticle(articleRequestDto);
        if (articleDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot update the article");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(articleDto);
    }

}
