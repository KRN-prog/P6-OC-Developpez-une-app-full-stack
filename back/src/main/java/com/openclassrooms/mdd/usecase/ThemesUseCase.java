package com.openclassrooms.mdd.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.service.ThemesService;
import com.openclassrooms.mdd.usecase.dto.request.ThemeRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemesUseCase {

    private final ThemesService themesService;

    public ResponseEntity<?> getAllThemes() {
        return themesService.getAllThemes();
    }

    public ResponseEntity<?> getThemesById(Long articleId) {
        return themesService.getThemeById(articleId);
    }

    public ResponseEntity<?> postNewTheme(ThemeRequestDto themeRequestDto) {
        return themesService.postNewTheme(themeRequestDto);
    }

}
