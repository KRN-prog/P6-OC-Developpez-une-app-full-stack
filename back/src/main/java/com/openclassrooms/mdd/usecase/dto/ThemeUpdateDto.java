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
public class ThemeUpdateDto {

    @JsonProperty("id_article")
    private int id_article;

    @JsonProperty("id_user")
    private int id_user;

}
