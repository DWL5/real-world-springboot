package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.response.ArticleMultipleResponse;
import org.example.realwordspringboot.controller.dto.response.ArticleSingleResponse;
import org.example.realwordspringboot.domain.article.Article;

import java.util.Set;

public class ArticleResponseMapper {
    public static ArticleMultipleResponse toMultipleResponse(Set<Article> article, String viewer) {
        var articleResponses = article.stream()
                .map(it -> toArticleResponse(it, viewer))
                .toList();

        return ArticleMultipleResponse.builder()
                .articleCount(articleResponses.size())
                .articles(articleResponses)
                .build();
    }

    public static ArticleSingleResponse toSingleResponse(Article article, String viewer) {
        var articleResponse = toArticleResponse(article, viewer);

        return ArticleSingleResponse.builder()
                .article(articleResponse)
                .build();
    }

    private static ArticleSingleResponse.ArticleResponse toArticleResponse(Article article, String viewer) {
        var author = article.getAuthor();
        var authorResponse = ArticleSingleResponse.AuthorResponse.builder()
                .userName(author.getUserName())
                .bio(author.getBio())
                .image(author.getImage())
                .following(author.isFollowing())
                .build();

        var favorited = article.getFavoriters().contains(viewer);
        return ArticleSingleResponse.ArticleResponse.builder()
                .slug(article.getSlug())
                .title(article.getTitle())
                .description(article.getDescription())
                .body(article.getBody())
                .tagList(article.getTagList())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .favoritesCount(article.getFavoriters().size())
                .favorited(favorited)
                .author(authorResponse)
                .build();
    }
}
