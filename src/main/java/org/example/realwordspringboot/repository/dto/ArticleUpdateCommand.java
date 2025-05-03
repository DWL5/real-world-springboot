package org.example.realwordspringboot.repository.dto;

import lombok.Builder;
import org.example.realwordspringboot.repository.entity.ArticleEntity;

import java.time.LocalDateTime;

@Builder
public record ArticleUpdateCommand(
        String title,
        String slug,
        String body,
        String description,
        LocalDateTime updateAt,
        Long articleId
) {
}
