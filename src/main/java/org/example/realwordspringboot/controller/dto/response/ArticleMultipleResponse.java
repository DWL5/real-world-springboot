package org.example.realwordspringboot.controller.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ArticleMultipleResponse(
        long articleCount,
        List<ArticleSingleResponse.ArticleResponse> articles
) {
}
