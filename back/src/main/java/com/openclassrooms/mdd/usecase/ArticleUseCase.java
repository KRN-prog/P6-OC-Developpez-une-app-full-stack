package com.openclassrooms.mdd.usecase;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.service.ArticleService;
import com.openclassrooms.mdd.usecase.dto.ArticleDto;
import com.openclassrooms.mdd.usecase.dto.request.ArticleRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleUseCase {

    private final ArticleService articleService;

    /**
     * Retrouver tout les articles
     * 
     * @return un liste de tout les articles existant
     */
    public List<ArticleDto> getAllArticles() {
        List<ArticleDto> articleDtos = articleService.getAllArticles();
        if (articleDtos == null) {
            return null;
        }
        return articleDtos;
    }

    /**
     * Retrouver l'utilisateur par l'id
     * 
     * @param articleId
     * @return un utilisateur retrouver par l'id
     */
    public ArticleDto getArticleById(Integer articleId) {
        ArticleDto articleDto = articleService.getArticleById(articleId);
        if (articleDto == null) {
            return null;
        }
        return articleDto;
    }

    /**
     * Modifier l'utilisateur par l'id
     * 
     * @param articleRequestDto
     * @param articleId
     * @return un utilisateur modifié
     */
    public ResponseEntity<?> updateArticleById(ArticleRequestDto articleRequestDto, int articleId) {
        return articleService.updateArticleById(articleRequestDto, articleId);
    }

    /**
     * Poster un nouvel article
     * 
     * @param articleRequestDto
     * @return un nouvelle article
     */
    public ArticleDto postNewArticle(ArticleRequestDto articleRequestDto) {
        return articleService.postNewArticle(articleRequestDto);
    }

}
