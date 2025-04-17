package org.example.realwordspringboot.controller.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ArticleResponse(
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean favorited,
        int favoritesCount,
        AuthorResponse author
) {

    @Builder
    public record AuthorResponse(
            String userName,
            String bio,
            String image,
            boolean following
    ) {

    }
}
