package com.openclassrooms.mdd.usecase.dto.request;

import lombok.Getter;

@Getter
public class ArticleRequestDto {
    
    private String titre;
    private String date;
    private String auteur;
    private String content;

}
