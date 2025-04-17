package org.example.realwordspringboot.repository.dto;

import lombok.Builder;
import org.example.realwordspringboot.repository.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ArticleCreateCommand(
        String title,
        String slug,
        String body,
        String description,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updateAt,
        UserEntity authorEntity
) {
}
