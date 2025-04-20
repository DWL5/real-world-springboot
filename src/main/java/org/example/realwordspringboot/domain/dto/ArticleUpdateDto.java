package org.example.realwordspringboot.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ArticleUpdateDto(
        String slug,
        String title,
        String description,
        String body,
        Long authorId
) {
}
