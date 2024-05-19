package com.openclassrooms.mdd.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.usecase.ThemesUseCase;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;

@RestController
@RequestMapping("/mdd")
public class ThemesController {

    private final ThemesUseCase themesUseCase;

    public ThemesController(ThemesUseCase themesUseCase) {
        this.themesUseCase = themesUseCase;
    }

    @GetMapping("/themes")
    public ResponseEntity<?> getAllThemes() {
        List<ThemeDto> themeDtos = this.themesUseCase.getAllThemes();

        if (themeDtos == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot retrieve the good article.");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(themeDtos);
    }

    @GetMapping("/theme/{id}")
    public ResponseEntity<?> getThemeById(@Valid @PathVariable Long id) {
        ThemeDto themeDto = this.themesUseCase.getThemesById(id);

        if (themeDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot retrieve the good article.");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(themeDto);
    }

}
