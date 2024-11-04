package org.example.realwordspringboot.controller.dto.response;


import lombok.Builder;

@Builder
public record UserResponse(
        String email,
        String token,
        String userName,
        String bio,
        String image
) {
}
