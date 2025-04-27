package org.example.realwordspringboot.domain.dto;

import lombok.Builder;

@Builder
public record ArticleDeleteDto(
        String slug,
        Long authorId
) {
}
