package org.example.realwordspringboot.controller.dto.reqeust;

public record LoginRequest(LoginUser user) {

    public record LoginUser(
            String email,
            String password) {

    }
}
