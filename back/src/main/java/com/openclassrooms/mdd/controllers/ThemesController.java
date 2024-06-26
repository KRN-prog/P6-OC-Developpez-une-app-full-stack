package com.openclassrooms.mdd.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mdd.models.ThemeSubEntity;
import com.openclassrooms.mdd.usecase.ThemesUseCase;
import com.openclassrooms.mdd.usecase.dto.ThemeDto;
import com.openclassrooms.mdd.usecase.dto.ThemeSubDto;
import com.openclassrooms.mdd.usecase.dto.request.DeleteThemeSubDto;
import com.openclassrooms.mdd.usecase.dto.request.ThemeSubRequestDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/mdd")
public class ThemesController {

    private final ThemesUseCase themesUseCase;

    public ThemesController(ThemesUseCase themesUseCase) {
        this.themesUseCase = themesUseCase;
    }

    /**
     * Retrouver les themes
     * 
     * @return un tableau contenant une liste de themes
     */
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

    /**
     * Retrouver un id par son id
     * 
     * @param id
     * @return un objet contenant un theme
     */
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

    /**
     * Permet de s'abonner à un theme
     * 
     * @param themeSubRequestDto
     * @return un abonnement à un theme qui est enregistrer en bdd
     */
    @PostMapping("/theme/sub")
    public ThemeSubEntity postNewSub(@Valid @RequestBody ThemeSubRequestDto themeSubRequestDto) {
        return themesUseCase.postNewSub(themeSubRequestDto);
    }

    /**
     * Retrouver l'abonnement à un theme par l'id
     * 
     * @param userId
     * @return un/plusieurs themes auquelles je me suis sub
     */
    @GetMapping("/theme/sub/{id}")
    public ResponseEntity<?> getThemesByUser(@PathVariable("id") Integer userId) {

        List<ThemeSubDto> themeDtos = themesUseCase.getThemesSub(userId);
        if (themeDtos == null) {
            return ResponseEntity.badRequest().body("null");
        }
        return ResponseEntity.ok(themeDtos);
    }

    /**
     * Permet de se désabonner d'un theme
     * 
     * @param deleteThemeSubDto
     * @return Une string pour signifier que l'abonnment est annulé
     */
    @DeleteMapping("/theme/sub")
    public ResponseEntity<?> deleteThemesByUser(@RequestBody DeleteThemeSubDto deleteThemeSubDto) {

        boolean themeDtos = themesUseCase.deleteThemesSub(deleteThemeSubDto);
        if (themeDtos == false) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Impossible de supprimer l'abonnement");
            return ResponseEntity.badRequest().body(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("response", "Abonnement supprimer avec succès");
        return ResponseEntity.ok(response);
    }

}
