package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.reqeust.UpdateUserRequest;
import org.example.realwordspringboot.domain.dto.UserUpdateDto;

public class UserUpdateDtoMapper {
    public static UserUpdateDto of(UpdateUserRequest.UpdateUser request, Long id) {
        return new UserUpdateDto(id, request.email(), request.bio(), request.image());
    }
}
