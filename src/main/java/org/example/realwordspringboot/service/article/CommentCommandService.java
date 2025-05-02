package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.repository.CommentRepository;
import org.example.realwordspringboot.repository.dto.CommentCreateCommand;
import org.example.realwordspringboot.repository.entity.CommentEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCommandService {
    private final CommentRepository commentRepository;

    public CommentEntity save(CommentCreateCommand commentCreateCommand) {
        var commentEntity = CommentEntity.builder()
                .author(commentCreateCommand.userEntity())
                .article(commentCreateCommand.articleEntity())
                .body(commentCreateCommand.comment().getBody())
                .createdAt(commentCreateCommand.comment().getCreatedAt())
                .updatedAt(commentCreateCommand.comment().getUpdatedAt())
                .build();

        return commentRepository.save(commentEntity);
    }
}
