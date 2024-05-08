package com.openclassrooms.mdd.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.usecase.UserUseCase;
import com.openclassrooms.mdd.usecase.dto.ThemeUpdateDto;
import com.openclassrooms.mdd.usecase.dto.UserDto;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/mdd/user")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@Valid @PathVariable("id") int userId)
            throws JsonMappingException, JsonProcessingException {
        return userUseCase.getUserById(userId);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@Valid @RequestBody ThemeUpdateDto themeUpdateDto) throws JsonMappingException, JsonProcessingException {
        return userUseCase.updateUser(themeUpdateDto);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> updateDeleteUser(@Valid @RequestBody ThemeUpdateDto themeUpdateDto) throws JsonMappingException, JsonProcessingException {
        return userUseCase.updateDeleteUser(themeUpdateDto);
    }

}
