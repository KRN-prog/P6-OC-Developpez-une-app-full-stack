package com.openclassrooms.mdd.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.repository.ThemesRepository;
import com.openclassrooms.mdd.usecase.dto.mapper.ThemeMapper;
import com.openclassrooms.mdd.usecase.dto.request.ThemeRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemesService {

    private final ThemesRepository themesRepository;

    public ResponseEntity<?> getThemeById(int articleId) {

        ThemesEntity theme = themesRepository.findById(articleId)
                .orElse(null);

        if (theme != null) {
            return ResponseEntity.ok(ThemeMapper.maptoThemesDto(theme));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Article error: Cannot retrieve the good article.");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> postNewTheme(ThemeRequestDto themeRequestDto) {

        ThemesEntity article = ThemeMapper.maptoThemesEntity(themeRequestDto);

        return ResponseEntity.ok(themesRepository.save(article));

    }

}
