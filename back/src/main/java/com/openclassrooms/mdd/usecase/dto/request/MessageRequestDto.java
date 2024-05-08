package com.openclassrooms.mdd.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {

    private String message;
    private Integer idArticle;
    private Integer idUser;

}
