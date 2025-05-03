package org.example.realwordspringboot.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ArticleCreateDto(
        String title,
        String description,
        String body,
        List<String> tagList,
        String authorName
) {
}
