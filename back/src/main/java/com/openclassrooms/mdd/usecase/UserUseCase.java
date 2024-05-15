package com.openclassrooms.mdd.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mdd.service.UserService;
import com.openclassrooms.mdd.usecase.dto.ThemeUpdateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserService userService;

    public ResponseEntity<?> getUserById(int userId) throws JsonMappingException, JsonProcessingException {
        return userService.getUserById(userId);
    }

    /*
     * public ResponseEntity<?> updateUser(ThemeUpdateDto themeUpdateDto) throws
     * JsonMappingException, JsonProcessingException {
     * return userService.updateUser(themeUpdateDto);
     * }
     */

    public ResponseEntity<?> updateDeleteUser(ThemeUpdateDto themeUpdateDto)
            throws JsonMappingException, JsonProcessingException {
        return userService.updateDeleteUser(themeUpdateDto);
    }

}
