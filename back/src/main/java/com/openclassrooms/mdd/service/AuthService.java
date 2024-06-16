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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private AuthRepository authRepository;

    public JWTService jwtService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(AuthRepository authRepository, JWTService jwtService) {
        this.authRepository = authRepository;
        this.jwtService = jwtService;
    }

    /**
     * Permet de retrouver un utilisateur par son email
     * 
     * @param userEmail
     * @return un utilisateur rerouvé par son email
     */
    public UserDto findByMail(String userEmail) {
        UserEntity userEntity = authRepository.findByEmail(userEmail).get();
        return UserMapper.maptoUserDto(userEntity);
    }

    /**
     * Permet de retrouver un utilisateur
     * 
     * @param authRequestDto
     * @param authentication
     * @return un token pour pouvoir identifier l'utilisateur
     * @throws JsonProcessingException
     */
    public TokenResponse getUser(AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {

        UserEntity user = authRepository
                .findByUsername(authRequestDto.getEmailOrUsername())
                .orElse(null);

        if (user == null) {
            user = authRepository
                    .findByEmail(authRequestDto.getEmailOrUsername())
                    .orElse(null);

            if (user == null) {
                return null;
            }

            if (passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
                UserDto userDto = UserMapper.maptoUserDto(user);

                String token = jwtService.genrerateToken(userDto, null, authentication);

                TokenResponse tokenResponse = new TokenResponse();
                tokenResponse.setToken(token);

                return tokenResponse;
            } else {
                return null;
            }
        }
        if (passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
            UserDto userDto = UserMapper.maptoUserDto(user);

            String token = jwtService.genrerateToken(userDto, null, authentication);

            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);

            return tokenResponse;
        } else {
            return null;
        }

    }

    /**
     * Permet d'enregistrer un utilisateur
     * 
     * @param userDto
     * @return un utilisater enregistrer en base de données
     */
    public UserEntity registerUser(UserDto userDto) {

        if (userDto.getEmail().contains("@")) {
            Integer formPassingCount = 0;
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            UserEntity user = authRepository.findByUsername(userDto.getUsername())
                    .orElse(null);
            if (user == null && formPassingCount == 0) {
                formPassingCount++;
                user = authRepository.findByEmail(userDto.getEmail())
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
