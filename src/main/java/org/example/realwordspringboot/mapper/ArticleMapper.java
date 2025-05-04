package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Author;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.entity.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleMapper {
    public static Article fromCreateDto(ArticleCreateDto articleCreateDto, User user) {
        var author = Author.builder()
                .userName(user.getUserName())
                .bio(user.getBio())
                .image(user.getImage())
                .following(false)
                .build();

        return Article.from(articleCreateDto, author);
    }

    public static Set<Article> toSet(List<ArticleEntity> entities, String viewerName) {
        return entities.stream()
                .map(entity -> fromEntity(entity, viewerName))
                .collect(Collectors.toSet());
    }

    public static Article fromEntity(ArticleEntity entity, String viewerName) {
        return Article.builder()
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .body(entity.getBody())
                .tagList(toTagList(entity.getArticleTags()))
                .comments(toComments(entity.getComments()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .author(toUser(entity.getAuthor(), viewerName))
                .favoriters(toFavoriter(entity.getFavorites()))
                .build();
    }

    private static List<String> toTagList(List<ArticleTagEntity> tagList) {
        return tagList.stream()
                .map(tagAndArticle -> tagAndArticle.getTag().getName())
                .toList();
    }

    private static Author toUser(UserEntity user, String viewerName) {
        var following = user.getFollowers().stream()
                .anyMatch(follower -> follower.getFollowerUser().getUserName().equals(viewerName));

        return Author.builder()
                .userName(user.getUserName())
                .bio(user.getBio())
                .image(user.getImage())
                .following(following)
                .build();
    }

    private static List<Comment> toComments(List<CommentEntity> commentEntities) {
        return commentEntities.stream()
                .map(CommentMapper::fromEntity)
                .collect(Collectors.toList());
    }

    private static List<String> toFavoriter(List<FavoriteEntity> favoriteEntities) {
        return favoriteEntities.stream()
                .map(favoriteEntity -> favoriteEntity.getUser().getUserName())
                .collect(Collectors.toList());
    }
}
