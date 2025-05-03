package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.repository.ArticleRepository;
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
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentEntity save(CommentCreateCommand commentCreateCommand) {
        var articleEntity = articleRepository.findBySlug(commentCreateCommand.slug());
        var commentEntity = CommentEntity.builder()
                .author(commentCreateCommand.userEntity())
                .article(articleEntity)
                .body(commentCreateCommand.comment().getBody())
                .createdAt(commentCreateCommand.comment().getCreatedAt())
                .updatedAt(commentCreateCommand.comment().getUpdatedAt())
                .build();

        return commentRepository.save(commentEntity);
    }

    public void delete(CommentDeleteCommand commentDeleteCommand) {
        var articleEntity = articleRepository.findBySlug(commentDeleteCommand.article().getSlug());
        articleEntity.getComments()
                .removeIf(comment -> comment.getId() == commentDeleteCommand.comment().getId());
    }
}
