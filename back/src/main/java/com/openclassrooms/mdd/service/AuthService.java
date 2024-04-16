package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void createUser(UserEntity user) {
        authRepository.save(user);
    }

    public Optional<UserEntity> getUserByEmail(String userEmail) {
        return authRepository.findByMail(userEmail);
    }
}
