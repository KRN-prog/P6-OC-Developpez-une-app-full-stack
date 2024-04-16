package com.openclassrooms.mdd.controllers;

import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.usecase.UserAuthUseCase;
import com.openclassrooms.mdd.usecase.dto.request.LoginRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAuthUseCase userAuthUseCase;

    public AuthController(UserAuthUseCase userAuthUseCase) {
        this.userAuthUseCase = userAuthUseCase;
    }

    @PostMapping(value = "/login")
    public Optional<UserEntity> login(@RequestBody LoginRequestDto loginRequestDto) {
        return userAuthUseCase.getUser(loginRequestDto);
    }

    @GetMapping(value = "/test")
    public String testController() {
        return "test test";
    }
}
