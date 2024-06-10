package com.openclassrooms.mdd.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mdd.service.AuthService;
import com.openclassrooms.mdd.usecase.UserAuthUseCase;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import com.openclassrooms.mdd.usecase.dto.response.TokenResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mdd/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private final UserAuthUseCase userAuthUseCase;

    public AuthController(UserAuthUseCase userAuthUseCase) {
        this.userAuthUseCase = userAuthUseCase;
    }

    /**
     * Permet de se connecter avec le username ou l'email et le mot de passe
     * 
     * @param authRequestDto
     * @param authentication
     * @return Retourn un token JWT
     * @throws JsonProcessingException
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {
        TokenResponse tokenResponse = userAuthUseCase.getUser(authRequestDto, authentication);
        Map<String, String> response = new HashMap<>();
        if (tokenResponse == null) {
            response.put("error", "Login error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok(objectMapper.writeValueAsString(tokenResponse));
    }

    /**
     * Permet de s'inscrire avec un email, username et mot de passe
     * 
     * @param userDto
     * @return un utilisateur enregistrer un base de données
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> postMethodName(@Valid @RequestBody UserDto userDto) {
        boolean user = userAuthUseCase.registerUser(userDto);
        Map<String, String> response = new HashMap<>();
        if (user == false) {
            response.put("error", "Register error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("response", "User register successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * Permet de s'authentifier
     * 
     * @return un utilisateur authentifié
     */
    @GetMapping(value = "/me")
    public UserDto createUserMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authService.findByMail(authentication.getName());
    }
}
