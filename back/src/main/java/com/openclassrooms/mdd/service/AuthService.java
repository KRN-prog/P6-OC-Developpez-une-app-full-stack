package com.openclassrooms.mdd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.repository.AuthRepository;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.mapper.UserMapper;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import com.openclassrooms.mdd.usecase.dto.response.TokenResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
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

        UserEntity user = authRepository
                .findByUsernameAndPassword(authRequestDto.getEmailOrUsername(), authRequestDto.getPassword())
                .orElse(null);

        if (user == null) {
            user = authRepository
                    .findByEmailAndPassword(authRequestDto.getEmailOrUsername(), authRequestDto.getPassword())
                    .orElse(null);

            if (user == null) {
                return null;
            }

            System.out.println(user);
            UserDto userDto = UserMapper.maptoUserDto(user);

            String token = jwtService.genrerateToken(userDto, null, authentication);

            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);

            return tokenResponse;
        }
        System.out.println(user);
        UserDto userDto = UserMapper.maptoUserDto(user);

        String token = jwtService.genrerateToken(userDto, null, authentication);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return tokenResponse;

    }

    public UserEntity registerUser(UserDto userDto) {

        System.out.println(userDto.getEmail());
        System.out.println(userDto.getUsername());
        if (userDto.getEmail().contains("@")) {
            Integer formPassingCount = 0;
            UserEntity user = authRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword())
                    .orElse(null);

            if (user == null && formPassingCount == 0) {
                formPassingCount++;
                user = authRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword())
                        .orElse(null);
                if (user == null && formPassingCount == 1) {
                    UserEntity userEntity = UserMapper.maptoUser(userDto);
                    return authRepository.save(userEntity);
                }
                return null;
            }
            return null;
        }

        return null;

    }

}
