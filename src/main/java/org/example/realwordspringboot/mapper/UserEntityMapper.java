package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.dto.RegistrationDto;
import org.example.realwordspringboot.repository.entity.UserEntity;

import java.util.ArrayList;

public class UserEntityMapper {
    public static UserEntity from(RegistrationDto dto) {
        return UserEntity.builder()
                .userName(dto.username())
                .password(dto.password())
                .email(dto.email())
                .followings(new ArrayList<>())
                .followers(new ArrayList<>())
                .build();
    }
}
