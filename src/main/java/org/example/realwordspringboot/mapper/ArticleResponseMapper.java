package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.response.ArticleResponse;
import org.example.realwordspringboot.domain.article.Article;

public class ArticleResponseMapper {
    public static ArticleResponse from(Article article) {
        var author = article.getAuthor();
        var authorResponse = ArticleResponse.AuthorResponse.builder()
                .userName(author.getUserName())
                .bio(author.getBio())
                .image(author.getImage())
                .following(author.isFollowing())
                .build();

        return ArticleResponse.builder()
                .slug(article.getSlug())
                .title(article.getTitle())
                .description(article.getDescription())
                .body(article.getBody())
                .tagList(article.getTagList())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .favorited(article.isFavorite())
                .favoritesCount(article.getFavoritesCount())
                .author(authorResponse)
                .build();
    }
}
