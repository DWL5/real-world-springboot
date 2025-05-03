package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.repository.dto.ArticleCreateCommand;
import org.example.realwordspringboot.repository.dto.ArticleUpdateCommand;
import org.example.realwordspringboot.repository.entity.ArticleEntity;
import org.example.realwordspringboot.repository.entity.UserEntity;

import java.time.LocalDateTime;

public class ArticleUpdateCommandMapper {

    public static ArticleUpdateCommand from(Article article) {

        return ArticleUpdateCommand.builder()
                .title(article.getTitle())
                .slug(article.getSlug())
                .body(article.getBody())
                .description(article.getDescription())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
