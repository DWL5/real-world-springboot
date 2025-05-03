package org.example.realwordspringboot.repository.dto;

import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Comment;

public record CommentDeleteCommand(
        Article article,
        Comment comment
) {
}
