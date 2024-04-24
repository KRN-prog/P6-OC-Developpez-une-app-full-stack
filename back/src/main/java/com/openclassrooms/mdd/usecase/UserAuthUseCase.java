package com.openclassrooms.mdd.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.mdd.service.AuthService;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthUseCase {

    private final AuthService authService;

    public ResponseEntity<?> getUser(AuthRequestDto authRequestDto, Authentication authentication)
            throws JsonProcessingException {
        return authService.getUser(authRequestDto, authentication);
    }

    public ResponseEntity<?> registerUser(UserDto userDto) {
        return authService.registerUser(userDto);
    }

}
