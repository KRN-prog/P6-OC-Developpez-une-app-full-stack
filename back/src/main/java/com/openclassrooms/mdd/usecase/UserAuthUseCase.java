package com.openclassrooms.mdd.usecase;

import com.openclassrooms.mdd.service.AuthService;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthUseCase {

    private final AuthService authService;

    public ResponseEntity<?> getUser(AuthRequestDto authRequestDto) {
        return authService.getUser(authRequestDto);
    }

    public ResponseEntity<?> registerUser(UserDto userDto) {
        return authService.registerUser(userDto);
    }

}
