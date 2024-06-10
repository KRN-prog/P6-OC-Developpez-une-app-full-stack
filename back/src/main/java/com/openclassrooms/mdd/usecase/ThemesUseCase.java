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

    /**
     * Retrouver tout les themes
     * 
     * @return une liste de themes
     */
    public List<ThemeDto> getAllThemes() {
        List<ThemeDto> themeDtos = themesService.getAllThemes();
        if (themeDtos == null) {
            return null;
        }

        return themeDtos;
    }

    /**
     * Retrouver un theme par id
     * 
     * @param articleId
     * @return un theme retrouver par id
     */
    public ThemeDto getThemesById(Long articleId) {
        ThemeDto themeDto = themesService.getThemeById(articleId);

        if (themeDto == null) {
            return null;
        }

        return themeDto;
    }

    /**
     * Poster un nouvelle abonnement en base de données
     * 
     * @param themeSubRequestDto
     * @return un theme qui à été enregistrer dans la base de données des
     *         abonnements
     */
    public ThemeSubEntity postNewSub(ThemeSubRequestDto themeSubRequestDto) {
        return themesService.postNewSub(themeSubRequestDto);
    }

    /**
     * Retrouver tout les themes abonnées
     * 
     * @param userId
     * @return une liste auquelle l'utilisateur c'est abonné
     */
    public List<ThemeSubDto> getThemesSub(Integer userId) {
        return themesService.getThemesSub(userId);
    }

    /**
     * Supprimer un abonnement d'un theme de la base de données
     * 
     * @param deleteThemeSubDto
     * @return un boolean pour savoir si l'utilisateur à bien été supprimer des
     *         themes abonné
     */
    public boolean deleteThemesSub(DeleteThemeSubDto deleteThemeSubDto) {
        return themesService.deleteThemesSub(deleteThemeSubDto);
    }

}
