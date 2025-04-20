package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.reqeust.ArticleCreateRequest;
import org.example.realwordspringboot.controller.dto.reqeust.ArticleUpdateRequest;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.dto.ArticleUpdateDto;

public class ArticleUpdateDtoMapper {

    public static ArticleUpdateDto of(ArticleUpdateRequest request, Long authorId, String slug) {
        return ArticleUpdateDto.builder()
                .slug(slug)
                .title(request.article().title())
                .description(request.article().description())
                .body(request.article().body())
                .authorId(authorId)
                .build();
    }
}
