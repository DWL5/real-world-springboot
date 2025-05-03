package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.reqeust.ArticleCreateRequest;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;

public class ArticleCreateDtoMapper {

    public static ArticleCreateDto of(ArticleCreateRequest request, String authorName) {
        return ArticleCreateDto.builder()
                .title(request.article().title())
                .description(request.article().description())
                .body(request.article().body())
                .tagList(request.article().tagList())
                .authorName(authorName)
                .build();
    }
}
