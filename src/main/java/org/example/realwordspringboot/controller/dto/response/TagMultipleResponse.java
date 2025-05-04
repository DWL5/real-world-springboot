package org.example.realwordspringboot.controller.dto.response;

import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record TagMultipleResponse(
        Set<String> tags
) {
}
