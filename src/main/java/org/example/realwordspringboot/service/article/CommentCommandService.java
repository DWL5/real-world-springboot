package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.repository.CommentRepository;
import org.example.realwordspringboot.repository.dto.CommentCreateCommand;
import org.example.realwordspringboot.repository.dto.CommentDeleteCommand;
import org.example.realwordspringboot.repository.entity.CommentEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    public void delete(CommentDeleteCommand commentDeleteCommand) {
        var articleEntity = commentDeleteCommand.articleEntity();
        articleEntity.getComments()
                .removeIf(comment -> comment.getId() == commentDeleteCommand.commentEntity().getId());
    }
}
