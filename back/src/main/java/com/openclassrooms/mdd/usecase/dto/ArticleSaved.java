package com.openclassrooms.mdd.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSaved {
    
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
    private int theme;
}
