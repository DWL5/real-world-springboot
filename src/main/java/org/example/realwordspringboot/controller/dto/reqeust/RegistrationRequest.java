package org.example.realwordspringboot.controller.dto.reqeust;

import org.example.realwordspringboot.domain.dto.RegistrationDto;

public record RegistrationRequest (
        RegisterUser user
) {
    public record RegisterUser(String username,
                               String email,
                               String password) {

    }

    public RegistrationDto toRegistrationDto() {
        return new RegistrationDto(user.username, user.email(), user().password());
    }
}
