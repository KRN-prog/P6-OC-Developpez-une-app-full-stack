package com.openclassrooms.mdd.usecase.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeRequestDto {

    private int id;

    @JsonProperty("id_user")
    private UserEntity idUser;

    @JsonProperty("id_theme")
    private ThemesEntity idTheme;

}
