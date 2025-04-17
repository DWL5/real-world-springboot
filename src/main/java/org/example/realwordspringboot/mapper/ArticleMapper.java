package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Author;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.user.User;

import java.time.LocalDateTime;

public class ArticleMapper {
    public static Article fromCreateDto(ArticleCreateDto articleCreateDto, User user) {
        var author = Author.builder()
                .userName(user.getUserName())
                .bio(user.getBio())
                .image(user.getImage())
                .build();

        return Article.builder()
                .slug(toSlug(articleCreateDto.title()))
                .title(articleCreateDto.title())
                .description(articleCreateDto.description())
                .body(articleCreateDto.body())
                .tagList(articleCreateDto.tagList())
                .favorite(false)
                .favoritesCount(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .author(author)
                .build();
    }

    private static String toSlug(String input) {
        return input.trim()
                .toLowerCase()
                .replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\s]", "") // 특수문자 제거 (한글 포함)
                .replaceAll("\\s+", "-"); // 공백 → 대시
    }
}
