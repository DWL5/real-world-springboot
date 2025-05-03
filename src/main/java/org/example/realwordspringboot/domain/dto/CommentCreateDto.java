package org.example.realwordspringboot.domain.dto;

public record CommentCreateDto(
        String body,
        String authorName,
        String slug
) {
}
