package com.openclassrooms.mdd.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.repository.AuthRepository;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.mapper.UserMapper;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import com.openclassrooms.mdd.usecase.dto.response.TokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;

    /*
     * public Optional<UserEntity> addNewTheme(int articleId) {
     * 
     * return authRepository.findById(articleId);
     * 
     * }
     */

    public ResponseEntity<?> addNewTheme(int articleId) throws JsonMappingException, JsonProcessingException {

        UserEntity user = authRepository.findById(5)
                .orElse(null);

        if (user != null) {
            UserDto userDto = UserMapper.maptoUserDto(user);
            // System.out.println(userDto.getThemes());
            System.out.println(user);

            // Récupérer les thèmes existants depuis le DTO
            String themesJsonString = userDto.getThemes();

            // Convertir la chaîne JSON en un tableau JSON
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode themesArray;
            try {
                themesArray = (ArrayNode) objectMapper.readTree(themesJsonString);
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer l'erreur de désérialisation JSON
                return null;
            }

            // Ajouter la nouvelle valeur au tableau JSON
            themesArray.add("newTheme");

            // Convertir le tableau JSON mis à jour en une chaîne JSON
            String updatedThemesJsonString;
            try {
                updatedThemesJsonString = objectMapper.writeValueAsString(themesArray);
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer l'erreur de sérialisation JSON
                return null;
            }

            // Mettre à jour les thèmes de l'entité avec les nouveaux thèmes combinés
            user.setThemes(updatedThemesJsonString);
            System.out.println(user);

            return ResponseEntity.ok("eeeeeeee");
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Login error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
