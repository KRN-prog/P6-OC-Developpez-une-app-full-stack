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

    public TokenResponse getUser(AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {

        UserEntity user = authRepository.findByUsernameOrEmail(authRequestDto.getEmail(), authRequestDto.getUsername())
                .orElse(null);

        if (user == null) {
            return null;
        }
        UserDto userDto = UserMapper.maptoUserDto(user);

        String token = jwtService.genrerateToken(userDto, null, authentication);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return tokenResponse;

    }

    public UserEntity registerUser(UserDto userDto) {

        UserEntity user = authRepository.findByUsernameOrEmail(userDto.getMail(), userDto.getUsername())
                .orElse(null);

        if (user == null) {
            UserEntity userEntity = UserMapper.maptoUser(userDto);
            return authRepository.save(userEntity);
        }
        return null;

    }

}
