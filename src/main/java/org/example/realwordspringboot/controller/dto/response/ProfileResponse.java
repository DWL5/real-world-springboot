package org.example.realwordspringboot.controller.dto.response;

import lombok.Builder;

@Builder
public record ProfileResponse(
        String username,
        String bio,
        String image,
        boolean following
) {
}
