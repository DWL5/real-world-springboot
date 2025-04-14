package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.response.UserResponse;
import org.example.realwordspringboot.domain.user.User;

public class UserResponseMapper {

    public static UserResponse toUserResponse(String token, User user) {
        return new UserResponse(user.getEmail(), token, user.getUserName(), user.getBio(), user.getImage());
    }
}
