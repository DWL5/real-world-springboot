package org.example.realwordspringboot.domain.dto;

public record CommentDeleteDto(Long authorId, Long commentId, String slug) {
}
