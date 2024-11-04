package org.example.realwordspringboot.controller.dto.reqeust;

public record RegistrationRequest (
        RegisterUser user
) {
    public record RegisterUser(String username,
                               String email,
                               String password) {

    }
}
