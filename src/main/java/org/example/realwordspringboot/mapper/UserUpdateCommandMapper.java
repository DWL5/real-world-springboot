package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.dto.UserUpdateCommand;
import org.example.realwordspringboot.repository.entity.UserEntity;

public class UserUpdateCommandMapper {
    public static UserUpdateCommand of(User user, UserEntity entity) {
        return new UserUpdateCommand(entity, user.getEmail(), user.getBio(), user.getImage());
    }
}
