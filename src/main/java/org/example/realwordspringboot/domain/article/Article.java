package org.example.realwordspringboot.domain.article;

import lombok.Builder;
import lombok.Getter;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.dto.ArticleUpdateDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class Article {
    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private List<Comment> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Author author;
    private List<String> favoriters;

    public static Article from(ArticleCreateDto articleCreateDto, Author author) {
        return Article.builder()
                .slug(toSlug(articleCreateDto.title()))
                .title(articleCreateDto.title())
                .description(articleCreateDto.description())
                .body(articleCreateDto.body())
                .tagList(articleCreateDto.tagList())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .favoriters(new ArrayList<>())
                .author(author)
                .build();
    }

    public void update(ArticleUpdateDto articleUpdateDto) {
        if (Objects.nonNull(articleUpdateDto.title())) {
            this.title = articleUpdateDto.title();
            this.slug = toSlug(title);
        }

        if (Objects.nonNull(articleUpdateDto.description())) {
            this.description = articleUpdateDto.description();
        }

        if (Objects.nonNull(articleUpdateDto.body())) {
            this.body = articleUpdateDto.body();
        }
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    public void favorite(String name) {
        favoriters.add(name);
    }

    public void unFavorite(String name) {
        favoriters.remove(name);
    }

    private static String toSlug(String input) {
        return input.trim()
                .toLowerCase()
                .replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\s]", "") // 특수문자 제거 (한글 포함)
                .replaceAll("\\s+", "-"); // 공백 → 대시
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(slug, article.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(slug);
    }
}
