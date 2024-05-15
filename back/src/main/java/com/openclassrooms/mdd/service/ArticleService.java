package com.openclassrooms.mdd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.openclassrooms.mdd.usecase.dto.ArticleDto;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.ThemesRepository;
import com.openclassrooms.mdd.usecase.dto.mapper.ArticleMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.ArticleRequestMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.ThemeMapper;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ThemesRepository themesRepository;

    public ResponseEntity<?> getAllArticles() {

        List<ArticleEntity> articles = articleRepository.findAll();

        if (articles != null) {
            List<ArticleDto> articleDtos = articles.stream()
                    .map(ArticleMapper::maptoArticleDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(articleDtos);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot retrieve articles.");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> getArticleById(int articleId) {

        ArticleEntity article = articleRepository.findByArticleId(articleId)
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
    public ResponseEntity<?> postNewArticle(ArticleRequestDto articleRequestDto) {

        ArticleEntity article = ArticleRequestMapper.maptoArticle(articleRequestDto);
        if (articleRepository.save(article) != null) {
                return ResponseEntity.ok(ArticleMapper.maptoArticleDto(article));
        }else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: An error has occured");
            return ResponseEntity.badRequest().body(response);
        }
        //return ResponseEntity.ok(articleRepository.save(article));
        /*if (article != null && article.getTitre().equals(articleRequestDto.getTitre())) {
            Optional<ThemesEntity> getTheme = themesRepository.findById((long) article.getArticleId());
            System.out.println(getTheme);
            if (getTheme != null) {
                article.getTheme().setId(getTheme.get().getId());
                System.out.println(article);
                return ResponseEntity.ok(articleRepository.save(article));
            }else{
                Map<String, String> response = new HashMap<>();
                response.put("error", "Theme error: Cannot retrieve the theme");
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot update the article");
            return ResponseEntity.badRequest().body(response);
        }*/

    }

}
