package com.openclassrooms.mdd.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.repository.AuthRepository;
import com.openclassrooms.mdd.usecase.dto.mapper.UserMapper;
import com.openclassrooms.mdd.usecase.dto.request.UpdateUserRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;

    public ResponseEntity<?> getUserById(int userId) throws JsonMappingException, JsonProcessingException {

        UserEntity user = authRepository.findByUserId(userId)
                .orElse(null);

        if (user != null) {
            return ResponseEntity.ok(UserMapper.maptoUserDto(user));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Login error: Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public UserEntity updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto) {

        Optional<UserEntity> optionalUser = authRepository.findById(userId);
        if (optionalUser.isPresent()) {

            UserEntity existingUser = optionalUser.get();
            existingUser.setUserId(existingUser.getUserId());
            existingUser.setUsername(updateUserRequestDto.getNewUsername());
            existingUser.setEmail(updateUserRequestDto.getNewEmail());
            existingUser.setPassword(existingUser.getPassword());
            return authRepository.save(existingUser);
        }

        return null;
    }

}
