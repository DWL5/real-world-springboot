package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.reqeust.ArticleUpdateRequest;
import org.example.realwordspringboot.domain.dto.ArticleUpdateDto;

public class ArticleUpdateDtoMapper {

    public static ArticleUpdateDto of(ArticleUpdateRequest request, String authorName, String slug) {
        return ArticleUpdateDto.builder()
                .slug(slug)
                .title(request.article().title())
                .description(request.article().description())
                .body(request.article().body())
                .authorName(authorName)
                .build();
    }
}
