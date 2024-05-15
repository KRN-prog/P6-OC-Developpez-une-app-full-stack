package com.openclassrooms.mdd.repository;

import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.usecase.dto.UserDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(Integer id);

    Optional<UserEntity> findByEmail(String mail);

    Optional<UserEntity> findByUsernameOrEmail(String mail, String username);

    void save(UserDto userDto);

}
