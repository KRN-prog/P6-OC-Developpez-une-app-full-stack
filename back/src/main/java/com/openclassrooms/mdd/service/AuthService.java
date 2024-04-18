package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.repository.AuthRepository;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.mapper.UserMapper;
import com.openclassrooms.mdd.usecase.dto.request.AuthRequestDto;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public ResponseEntity<?> getUser(AuthRequestDto authRequestDto) {

        UserEntity user = authRepository.findByUsernameOrMail(authRequestDto.getEmail(), authRequestDto.getUsername())
                .orElse(null);

        if (user != null && user.getPassword().equals(authRequestDto.getPassword())) {
            return ResponseEntity.ok(UserMapper.maptoUserDto(authRepository.save(user)));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Login error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> registerUser(UserDto userDto) {

        UserEntity user = authRepository.findByUsernameOrMail(userDto.getMail(), userDto.getUsername())
                .orElse(null);

        if (user == null) {
            UserEntity userEntity = UserMapper.maptoUser(userDto);
            authRepository.save(userEntity);
            return ResponseEntity.ok("User register successfully");
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Register error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
