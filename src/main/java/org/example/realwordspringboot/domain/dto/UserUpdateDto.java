package org.example.realwordspringboot.domain.dto;

public record UserUpdateDto(
        String userName,
        String email,
        String bio,
        String image
) {
}
