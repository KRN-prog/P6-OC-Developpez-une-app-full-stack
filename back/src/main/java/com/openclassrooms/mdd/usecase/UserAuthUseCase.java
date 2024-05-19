package com.openclassrooms.mdd.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.service.AuthService;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import com.openclassrooms.mdd.usecase.dto.response.TokenResponse;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthUseCase {

    private final AuthService authService;

    public TokenResponse getUser(AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {
        TokenResponse getUser = authService.getUser(authRequestDto, authentication);
        if (getUser == null) {
            return null;
        }
        return getUser;
    }

    public Map<String, String> registerUser(UserDto userDto) {
        UserEntity registerUser = authService.registerUser(userDto);
        Map<String, String> response = new HashMap<>();
        if (registerUser == null) {
            response.put("error", "Register error: Invalid credentials");
            System.out.println(response);

            return response;
        }
        response.put("response", "User register successfully");
        System.out.println(response);
        return response;
    }

}
