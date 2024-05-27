package com.openclassrooms.mdd.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.models.ThemeSubEntity;
import com.openclassrooms.mdd.service.ThemesService;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;
import com.openclassrooms.mdd.usecase.dto.ThemeSubDto;
import com.openclassrooms.mdd.usecase.dto.request.DeleteThemeSubDto;
import com.openclassrooms.mdd.usecase.dto.request.ThemeSubRequestDto;

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

    public ThemeSubEntity postNewSub(ThemeSubRequestDto themeSubRequestDto) {
        return themesService.postNewSub(themeSubRequestDto);
    }

    public List<ThemeSubDto> getThemesSub(Integer userId) {
        return themesService.getThemesSub(userId);
    }

    public boolean deleteThemesSub(DeleteThemeSubDto deleteThemeSubDto) {
        return themesService.deleteThemesSub(deleteThemeSubDto);
    }

}
