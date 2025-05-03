package org.example.realwordspringboot.domain.dto;

public record CommentDeleteDto(String authorName, Long commentId, String slug) {
}
