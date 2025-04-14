package org.example.realwordspringboot.repository.dto;

import org.example.realwordspringboot.repository.entity.UserEntity;

public record UserUpdateCommand(
        UserEntity userEntity,
        String email,
        String bio,
        String image
) {
}
