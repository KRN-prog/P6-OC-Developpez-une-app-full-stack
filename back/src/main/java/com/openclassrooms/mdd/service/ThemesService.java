package com.openclassrooms.mdd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.repository.ThemesRepository;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;
import com.openclassrooms.mdd.usecase.dto.mapper.ThemeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemesService {

    private final ThemesRepository themesRepository;

    public List<ThemeDto> getAllThemes() {

        List<ThemesEntity> theme = themesRepository.findAll();

        if (theme == null) {
            return null;
        }

        List<ThemeDto> themesDtos = theme.stream()
                .map(ThemeMapper::maptoThemesDto)
                .collect(Collectors.toList());
        return themesDtos;
    }

    public ThemeDto getThemeById(Long articleId) {

        ThemesEntity theme = themesRepository.findById(articleId)
                .orElse(null);

        if (theme == null) {
            return null;
        }

        /*
         * Map<String, String> response = new HashMap<>();
         * response.put("error", "Article error: Cannot retrieve the good article.");
         * return ResponseEntity.badRequest().body(response);
         */

        return ThemeMapper.maptoThemesDto(theme);
    }

}
