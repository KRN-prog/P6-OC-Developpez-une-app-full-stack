package com.openclassrooms.mdd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mdd.models.ThemesEntity;

@Repository
public interface ThemesRepository extends JpaRepository<ThemesEntity, Long> {

    Optional<ThemesEntity> findById(Integer articleIdInteger);

}
