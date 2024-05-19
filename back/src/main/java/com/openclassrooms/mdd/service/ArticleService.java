package com.openclassrooms.mdd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.openclassrooms.mdd.usecase.dto.ArticleDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.usecase.dto.mapper.ArticleMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.ArticleRequestMapper;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDto> getAllArticles() {

        List<ArticleEntity> articles = articleRepository.findAll();

        if (articles == null) {
            return null;
        }
        List<ArticleDto> articleDtos = articles.stream()
                .map(ArticleMapper::maptoArticleDto)
                .collect(Collectors.toList());
        return articleDtos;

    }

    public ArticleDto getArticleById(int articleId) {

        ArticleEntity article = articleRepository.findByArticleId(articleId)
                .orElse(null);

        if (article == null) {
            return null;
        }
        return ArticleMapper.maptoArticleDto(article);

    }

    public ResponseEntity<?> updateArticleById(ArticleRequestDto articleRequestDto, int articleId) {

        ArticleEntity article = articleRepository.findByArticleId(articleId)
                .orElse(null);

        if (article != null && article.getTitre().equals(articleRequestDto.getTitre())) {
            return ResponseEntity.ok(ArticleMapper.maptoArticleDto(articleRepository.save(article)));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot update the article");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @Transactional
    public ArticleDto postNewArticle(ArticleRequestDto articleRequestDto) {
        ArticleEntity article = ArticleRequestMapper.maptoArticle(articleRequestDto);
        articleRepository.save(article);
        return ArticleMapper.maptoArticleDto(article);
    }

}
