package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Author;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.entity.ArticleEntity;
import org.example.realwordspringboot.repository.entity.ArticleTagEntity;
import org.example.realwordspringboot.repository.entity.CommentEntity;
import org.example.realwordspringboot.repository.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleMapper {
    public static Article fromCreateDto(ArticleCreateDto articleCreateDto, User user) {
        var author = Author.builder()
                .userName(user.getUserName())
                .bio(user.getBio())
                .image(user.getImage())
                .build();

        return Article.from(articleCreateDto, author);
    }

    public static Article fromEntity(ArticleEntity entity) {
        return Article.builder()
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .body(entity.getBody())
                .tagList(toTagList(entity.getArticleTags()))
                .comments(toComments(entity.getComments()))
                .favorite(false) //TODO : 구현필요
                .favoritesCount(0)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .author(toUser(entity.getAuthor()))
                .build();
    }

    private static List<String> toTagList(List<ArticleTagEntity> tagList) {
        return tagList.stream()
                .map(tagAndArticle -> tagAndArticle.getTag().getName())
                .toList();
    }

    private static Author toUser(UserEntity user) {
        return Author.builder()
                .userName(user.getUserName())
                .bio(user.getBio())
                .image(user.getImage())
                .following(false)
                .build();
    }

    private static List<Comment> toComments(List<CommentEntity> commentEntities) {
        return commentEntities.stream()
                .map(CommentMapper::fromEntity)
                .collect(Collectors.toList());
    }
}
