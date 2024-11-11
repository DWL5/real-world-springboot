package org.example.realwordspringboot.controller.dto.reqeust;

public record UpdateUserRequest(
        UpdateUser user

) {
    public record UpdateUser(
            String email,
            String bio,
            String image
    ) {

    }
}
