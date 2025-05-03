package org.example.realwordspringboot.service.user;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.controller.dto.response.UserResponse;
import org.example.realwordspringboot.domain.dto.LoginDto;
import org.example.realwordspringboot.domain.dto.RegistrationDto;
import org.example.realwordspringboot.domain.dto.UserUpdateDto;

public interface UserService {
    UserResponse register(RegistrationDto registrationDto);

    UserResponse login(LoginDto loginDto) throws BadRequestException;

    UserResponse findUser(String userName) throws BadRequestException;

    UserResponse updateUser(UserUpdateDto command) throws BadRequestException;
}
