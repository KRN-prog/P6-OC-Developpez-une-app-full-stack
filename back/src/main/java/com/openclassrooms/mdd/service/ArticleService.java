package com.openclassrooms.mdd.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.usecase.dto.mapper.ArticleMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.ArticleRequestMapper;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ResponseEntity<?> getAllArticles() {

        List articles = articleRepository.findAll();

        if (articles != null) {
            List<ArticleDto> articleDtos = articles.stream()
                    .map(ArticleMapper::maptoArticleDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ArticleMapper.maptoArticleDto(articles));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot retrieve the good article.");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> getArticleById(int articleId) {

        ArticleEntity article = articleRepository.findById(articleId)
                .orElse(null);

        if (article != null) {
            return ResponseEntity.ok(ArticleMapper.maptoArticleDto(article));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot retrieve the good article.");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> updateArticleById(ArticleRequestDto articleRequestDto, int articleId) {

        ArticleEntity article = articleRepository.findById(articleId)
                .orElse(null);

        if (article != null && article.getTitre().equals(articleRequestDto.getTitre())) {
            return ResponseEntity.ok(ArticleMapper.maptoArticleDto(articleRepository.save(article)));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot update the article");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> postNewArticle(ArticleRequestDto articleRequestDto) {

        ArticleEntity article = ArticleRequestMapper.maptoArticle(articleRequestDto);

        return ResponseEntity.ok(articleRepository.save(article));

    }

}
