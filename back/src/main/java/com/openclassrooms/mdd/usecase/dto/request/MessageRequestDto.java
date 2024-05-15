package com.openclassrooms.mdd.usecase.dto.request;

import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {

    private String message;
    private ArticleEntity idArticle;
    private UserEntity idUser;

}
