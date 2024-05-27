package com.openclassrooms.mdd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.ThemeSubEntity;
import com.openclassrooms.mdd.models.ThemesEntity;
import com.openclassrooms.mdd.repository.ThemeSubRepository;
import com.openclassrooms.mdd.repository.ThemesRepository;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;
import com.openclassrooms.mdd.usecase.dto.ThemeSubDto;
import com.openclassrooms.mdd.usecase.dto.mapper.ThemeMapper;
import com.openclassrooms.mdd.usecase.dto.mapper.ThemeSubMapper;
import com.openclassrooms.mdd.usecase.dto.request.DeleteThemeSubDto;
import com.openclassrooms.mdd.usecase.dto.request.ThemeSubRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemesService {

    private final ThemesRepository themesRepository;
    private final ThemeSubRepository themeSubRepository;

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
        return ThemeMapper.maptoThemesDto(theme);
    }

    public ThemeSubEntity postNewSub(ThemeSubRequestDto themeSubDto) {
        ThemeSubEntity theme_sub = ThemeSubMapper.maptoThemesSub(themeSubDto);
        System.out.println(themeSubDto.getUser());
        System.out.println(themeSubDto.getTheme());
        return themesRepository.save(theme_sub);
    }

    public List<ThemeSubDto> getThemesSub(Integer userId) {
        List<ThemeSubEntity> themes = themeSubRepository.findByUser_UserId(userId);

        if (themes == null) {
            return null;
        }
        List<ThemeSubDto> themesConverter = themes.stream()
                .map(ThemeSubMapper::mapToThemesSubDto)
                .collect(Collectors.toList());
        return themesConverter;
    }

    public boolean deleteThemesSub(DeleteThemeSubDto deleteThemeSubDto) {
        ThemeSubEntity theme = themeSubRepository.findByUser_UserIdAndTheme_Id(deleteThemeSubDto.getUserId(),
                deleteThemeSubDto.getThemeId());

        if (theme == null) {
            return false;
        }
        themeSubRepository.delete(theme);
        /*
         * List<ThemeSubDto> themesConverter = themes.stream()
         * .map(ThemeSubMapper::mapToThemesSubDto)
         * .collect(Collectors.toList());
         */
        return true;
    }

}
