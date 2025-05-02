package org.example.realwordspringboot.domain.dto;

public record CommentCreateDto(
        String body,
        Long authorId,
        String slug
) {
}
