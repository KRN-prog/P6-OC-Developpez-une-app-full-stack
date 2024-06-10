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

    /**
     * Permet de retrouver tout les themes
     * 
     * @return une liste de tout les themes existant
     */
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

    /**
     * Permet de retrouver un theme par id
     * 
     * @param articleId
     * @return un theme par id
     */
    public ThemeDto getThemeById(Long articleId) {

        ThemesEntity theme = themesRepository.findById(articleId)
                .orElse(null);

        if (theme == null) {
            return null;
        }
        return ThemeMapper.maptoThemesDto(theme);
    }

    /**
     * Poster un nouveau theme
     * 
     * @param themeSubDto
     * @return un theme enregistrer en bdd
     */
    public ThemeSubEntity postNewSub(ThemeSubRequestDto themeSubDto) {
        ThemeSubEntity theme_sub = ThemeSubMapper.maptoThemesSub(themeSubDto);
        System.out.println(themeSubDto.getUser());
        System.out.println(themeSubDto.getTheme());
        return themesRepository.save(theme_sub);
    }

    /**
     * Retrouver tout les themes auquelles l'utilisateur c'est abonné
     * 
     * @param userId
     * @return une liste de theme retrouver via l'id de l'utilisateur
     */
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

    /**
     * Supprimer l'abonnement à un theme
     * 
     * @param deleteThemeSubDto
     * @return un boolean pour savoir si l'utilisateur est bien désabonné
     */
    public boolean deleteThemesSub(DeleteThemeSubDto deleteThemeSubDto) {
        ThemeSubEntity theme = themeSubRepository.findByUser_UserIdAndTheme_Id(deleteThemeSubDto.getUserId(),
                deleteThemeSubDto.getThemeId());

        if (theme == null) {
            return false;
        }
        themeSubRepository.delete(theme);
        return true;
    }

}
