package com.openclassrooms.mdd.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.mdd.service.AuthService;
import com.openclassrooms.mdd.usecase.UserAuthUseCase;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/mdd/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private final UserAuthUseCase userAuthUseCase;

    public AuthController(UserAuthUseCase userAuthUseCase) {
        this.userAuthUseCase = userAuthUseCase;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {
        return userAuthUseCase.getUser(authRequestDto, authentication);
    }

    @PostMapping("/register")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody UserDto userDto) {
        return userAuthUseCase.registerUser(userDto);
    }

    @GetMapping(value = "/me")
    public UserDto createUserMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authService.findByMail(authentication.getName());
    }
}
