package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.repository.dto.ArticleCreateCommand;
import org.example.realwordspringboot.repository.entity.UserEntity;

public class ArticleCreateCommandMapper {
    public static ArticleCreateCommand from(Article article, UserEntity userEntity) {

        return ArticleCreateCommand.builder()
                .title(article.getTitle())
                .slug(article.getSlug())
                .body(article.getBody())
                .description(article.getDescription())
                .tagList(article.getTagList())
                .authorEntity(userEntity)
                .build();
    }
}
