package org.example.realwordspringboot.domain.dto;

public record UserUpdateDto(
        Long id,
        String email,
        String bio,
        String image
) {
}
