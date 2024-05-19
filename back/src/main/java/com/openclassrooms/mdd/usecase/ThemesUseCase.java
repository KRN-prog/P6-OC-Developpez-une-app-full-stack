package com.openclassrooms.mdd.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.service.ThemesService;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemesUseCase {

    private final ThemesService themesService;

    public List<ThemeDto> getAllThemes() {
        List<ThemeDto> themeDtos = themesService.getAllThemes();
        if (themeDtos == null) {
            return null;
        }

        return themeDtos;
    }

    public ThemeDto getThemesById(Long articleId) {
        ThemeDto themeDto = themesService.getThemeById(articleId);

        if (themeDto == null) {
            return null;
        }

        return themeDto;
    }

}
