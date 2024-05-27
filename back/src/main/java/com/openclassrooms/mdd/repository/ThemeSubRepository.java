package com.openclassrooms.mdd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mdd.models.ThemeSubEntity;

@Repository
public interface ThemeSubRepository extends JpaRepository<ThemeSubEntity, Long> {
    List<ThemeSubEntity> findByUser_UserId(Integer userId);

    ThemeSubEntity findByUser_UserIdAndTheme_Id(Integer userId, Integer themeId);
}
