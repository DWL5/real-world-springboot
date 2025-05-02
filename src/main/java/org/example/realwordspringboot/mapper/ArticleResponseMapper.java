package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.response.ArticleSingleResponse;
import org.example.realwordspringboot.domain.article.Article;

public class ArticleResponseMapper {
    public static ArticleSingleResponse from(Article article) {
        var author = article.getAuthor();
        var authorResponse = ArticleSingleResponse.AuthorResponse.builder()
                .userName(author.getUserName())
                .bio(author.getBio())
                .image(author.getImage())
                .following(author.isFollowing())
                .build();

        var articleResponse = ArticleSingleResponse.ArticleResponse.builder()
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

        return ArticleSingleResponse.builder()
                .article(articleResponse)
                .build();
    }
}
