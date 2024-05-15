package com.openclassrooms.mdd.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mdd.models.ArticleEntity;
import com.openclassrooms.mdd.models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private int id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("id_article")
    private ArticleEntity idArticle;

    @JsonProperty("id_user")
    private UserEntity idUser;

}
