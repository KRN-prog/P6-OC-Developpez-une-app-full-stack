package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.usecase.dto.UserDto;

public class UserMapper {
    public static UserDto maptoUserDto(UserEntity userEntity) {
        return new UserDto(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword());
    }

    public static UserEntity maptoUser(UserDto userDto) {
        return new UserEntity(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getMail(),
                userDto.getPassword());
    }
}
