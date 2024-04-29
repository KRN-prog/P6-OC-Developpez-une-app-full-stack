package com.openclassrooms.mdd.usecase;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.service.UserService;
import com.openclassrooms.mdd.usecase.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserService userService;

    public ResponseEntity<?> addNewTheme(int articleId) throws JsonMappingException, JsonProcessingException {
        return userService.addNewTheme(5);
    }

}
