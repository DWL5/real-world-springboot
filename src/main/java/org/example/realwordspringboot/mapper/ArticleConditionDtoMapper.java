package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.reqeust.ArticleRequest;
import org.example.realwordspringboot.domain.dto.ArticleConditionDto;
import org.springframework.data.domain.Pageable;


public class ArticleConditionDtoMapper {
    public static ArticleConditionDto of(ArticleRequest articleRequest, Pageable pageable) {
        var offset = pageable != null ? pageable.getOffset() : 0;
        var limit = pageable != null ? pageable.getPageSize() : 20;

        return ArticleConditionDto.builder()
                .authorName(articleRequest.author())
                .favoritedUserName(articleRequest.favorited())
                .tagName(articleRequest.tag())
                .offset(offset)
                .limit(limit)
                .build();
    }
}
