package com.openclassrooms.mdd.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.usecase.UserUseCase;
import com.openclassrooms.mdd.usecase.dto.request.UpdateUserRequestDto;

import java.util.HashMap;
import java.util.Map;

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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable("id") Long userId,
            @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        UserEntity updateUser = userUseCase.updateUser(userId, updateUserRequestDto);
        if (updateUser == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Impossible to update the user");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(updateUser);
    }

}
