package org.example.realwordspringboot.repository.dto;

import org.example.realwordspringboot.repository.entity.ArticleEntity;
import org.example.realwordspringboot.repository.entity.CommentEntity;

public record CommentDeleteCommand(
        ArticleEntity articleEntity,
        CommentEntity commentEntity
) {
}
