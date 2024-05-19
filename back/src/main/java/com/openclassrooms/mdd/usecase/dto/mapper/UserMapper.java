package com.openclassrooms.mdd.usecase.dto.mapper;

import com.openclassrooms.mdd.models.UserEntity;
import com.openclassrooms.mdd.usecase.dto.UserDto;
import com.openclassrooms.mdd.usecase.dto.request.UpdateUserRequestDto;

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

    public static UserEntity maptoUser(UpdateUserRequestDto updateUserRequestDto) {
        return new UserEntity(
                null,
                updateUserRequestDto.getNewUsername(),
                updateUserRequestDto.getNewEmail(),
                null);
    }
}
