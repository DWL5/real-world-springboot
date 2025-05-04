package org.example.realwordspringboot.domain.dto;

import lombok.Builder;

@Builder
public record ArticleConditionDto(
        String tagName,
        String authorName,
        String favoritedUserName,
        long limit,
        long offset
) {
}
