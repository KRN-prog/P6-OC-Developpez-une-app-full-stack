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
public class MessageDto {

    private Integer id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("id_article")
    private Integer idArticle;

    @JsonProperty("id_user")
    private Integer idUser;

}
