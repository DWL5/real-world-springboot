package org.example.realwordspringboot.controller.dto.reqeust;

import org.example.realwordspringboot.domain.dto.LoginDto;

public record LoginRequest(LoginUser user) {

    public record LoginUser(
            String email,
            String password) {

    }

    public LoginDto toLoginDto() {
        return new LoginDto(user.email, user.password);
    }
}
