package com.openclassrooms.mdd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.usecase.ThemesUseCase;
import com.openclassrooms.mdd.usecase.dto.request.ThemeRequestDto;

@RestController
@RequestMapping("/mdd")
public class ThemesController {

    private final ThemesUseCase themesUseCase;

    public ThemesController(ThemesUseCase themesUseCase) {
        this.themesUseCase = themesUseCase;
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getThemeById(@Valid @RequestBody Integer id) {
        return this.themesUseCase.getThemesById(id);
    }

    @PostMapping("/article")
    public ResponseEntity<?> postNewTheme(@Valid @RequestBody ThemeRequestDto themeRequestDto) {
        return this.themesUseCase.postNewTheme(themeRequestDto);
    }

}
