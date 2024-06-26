package com.openclassrooms.mdd.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mdd.models.ThemesEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private Long id;

    @JsonProperty("titre")
    private String titre;

    @JsonProperty("date")
    private String date;

    @JsonProperty("auteur")
    private String auteur;

    @JsonProperty("content")
    private String content;

    @JsonProperty("theme")
    private ThemesEntity theme;
}