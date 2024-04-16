package com.openclassrooms.mdd.usecase;

import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.service.AuthService;
import com.openclassrooms.mdd.usecase.dto.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthUseCase {

    private final AuthService authService;

    public Optional<UserEntity> getUser(LoginRequestDto loginRequestDto){
        return authService.getUserByEmail(loginRequestDto.getEmail());
    }

}
