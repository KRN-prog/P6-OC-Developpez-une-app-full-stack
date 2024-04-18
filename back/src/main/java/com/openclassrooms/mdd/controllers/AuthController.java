package com.openclassrooms.mdd.controllers;

import com.openclassrooms.mdd.usecase.UserAuthUseCase;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/mdd/auth")
public class AuthController {

    private final UserAuthUseCase userAuthUseCase;

    public AuthController(UserAuthUseCase userAuthUseCase) {
        this.userAuthUseCase = userAuthUseCase;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        return userAuthUseCase.getUser(authRequestDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody UserDto userDto) {
        return userAuthUseCase.registerUser(userDto);
    }
}
