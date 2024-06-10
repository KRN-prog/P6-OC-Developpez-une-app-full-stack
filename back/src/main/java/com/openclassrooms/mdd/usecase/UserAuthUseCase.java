package com.openclassrooms.mdd.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.service.AuthService;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import com.openclassrooms.mdd.usecase.dto.response.TokenResponse;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthUseCase {

    private final AuthService authService;

    /**
     * Retrouver un utilisateur via l'authentication et l'email ou le username
     * 
     * @param authRequestDto
     * @param authentication
     * @return un utilisateur
     * @throws JsonProcessingException
     */
    public TokenResponse getUser(AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {
        TokenResponse getUser = authService.getUser(authRequestDto, authentication);
        if (getUser == null) {
            return null;
        }
        return getUser;
    }

    /**
     * Enregistrer un utilisateur
     * 
     * @param userDto
     * @return un utilisateur enregistrer
     */
    public boolean registerUser(UserDto userDto) {
        UserEntity registerUser = authService.registerUser(userDto);
        if (registerUser == null) {
            return false;
        }
        return true;
    }

}
