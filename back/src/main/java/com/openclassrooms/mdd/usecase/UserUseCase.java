package com.openclassrooms.mdd.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.service.UserService;
import com.openclassrooms.mdd.usecase.dto.request.UpdateUserRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserService userService;

    /**
     * Retrouver l'utilisateur par l'id
     * 
     * @param userId
     * @return un utilisateur
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public ResponseEntity<?> getUserById(int userId) throws JsonMappingException, JsonProcessingException {
        return userService.getUserById(userId);
    }

    /**
     * Modifier l'utilisateur
     * 
     * @param userId
     * @param updateUserRequestDto
     * @return un utilisateur modifié
     */
    public UserEntity updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto) {
        return userService.updateUser(userId, updateUserRequestDto);
    }

}
