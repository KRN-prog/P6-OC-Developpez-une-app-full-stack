package com.openclassrooms.mdd.repository;

import com.openclassrooms.mdd.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository  extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity>findByMail(String mail);
}
