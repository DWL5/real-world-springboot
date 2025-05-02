package org.example.realwordspringboot.repository.dto;

import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.repository.entity.ArticleEntity;
import org.example.realwordspringboot.repository.entity.UserEntity;

public record CommentCreateCommand(
        Comment comment,
        ArticleEntity articleEntity,
        UserEntity userEntity
) {
}
