package com.openclassrooms.mdd.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDto {

    private String titre;
    private String date;
    private String auteur;
    private String content;

}
