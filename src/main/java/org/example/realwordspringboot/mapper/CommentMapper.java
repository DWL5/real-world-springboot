package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.article.Author;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.domain.dto.CommentCreateDto;
import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.entity.CommentEntity;

public class CommentMapper {

    public static Comment fromCreateDto(CommentCreateDto commentCreateDto, User user) {
        var author = Author.builder()
                .userName(user.getUserName())
                .bio(user.getBio())
                .image(user.getImage())
                .build();

        return Comment.create(commentCreateDto, author);
    }

    public static Comment fromEntity(CommentEntity entity) {
        var user = entity.getAuthor();
        var author = Author.builder()
                .userName(user.getUserName())
                .bio(user.getBio())
                .image(user.getImage())
                .build();

        return Comment.builder()
                .id(entity.getId())
                .body(entity.getBody())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .author(author)
                .build();
    }
}
