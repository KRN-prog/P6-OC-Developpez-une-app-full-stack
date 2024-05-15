package com.openclassrooms.mdd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.repository.AuthRepository;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.mapper.UserMapper;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import com.openclassrooms.mdd.usecase.dto.response.TokenResponse;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private AuthRepository authRepository;

    public JWTService jwtService;

    @Autowired
    public AuthService(AuthRepository authRepository, JWTService jwtService) {
        this.authRepository = authRepository;
        this.jwtService = jwtService;
    }

    public UserDto findByMail(String userEmail) {
        UserEntity userEntity = authRepository.findByEmail(userEmail).get();
        return UserMapper.maptoUserDto(userEntity);
    }

    public ResponseEntity<?> getUser(AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {

        UserEntity user = authRepository.findByUsernameOrEmail(authRequestDto.getEmail(), authRequestDto.getUsername())
                .orElse(null);

        if (user != null && user.getPassword().equals(authRequestDto.getPassword())) {
            UserDto userDto = UserMapper.maptoUserDto(user);

            String token = jwtService.genrerateToken(userDto, null, authentication);

            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);

            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(tokenResponse));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Login error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> registerUser(UserDto userDto) {

        UserEntity user = authRepository.findByUsernameOrEmail(userDto.getMail(), userDto.getUsername())
                .orElse(null);

        if (user == null) {
            UserEntity userEntity = UserMapper.maptoUser(userDto);
            authRepository.save(userEntity);
            Map<String, String> response = new HashMap<>();
            response.put("response", "User register successfully");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Register error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
