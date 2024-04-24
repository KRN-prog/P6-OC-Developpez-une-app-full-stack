package com.openclassrooms.mdd.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDto {

    private int id;

    @JsonProperty("theme")
    private String theme;

    @JsonProperty("title")
    private String title;

    @JsonProperty("contenu")
    private String contenu;

}
