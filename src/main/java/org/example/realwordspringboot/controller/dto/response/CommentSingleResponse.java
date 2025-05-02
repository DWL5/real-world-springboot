package org.example.realwordspringboot.controller.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentSingleResponse(
        CommentResponse comment
) {

    @Builder
    public record CommentResponse(
            Long id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String body,
            AuthorResponse author
    ) {

    }

    @Builder
    public record AuthorResponse(
            String userName,
            String bio,
            String image,
            boolean following
    ) {

    }
}
