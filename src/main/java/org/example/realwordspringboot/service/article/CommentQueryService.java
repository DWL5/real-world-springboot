package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.mapper.CommentMapper;
import org.example.realwordspringboot.repository.ArticleRepository;
import org.example.realwordspringboot.repository.CommentRepository;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentQueryService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public List<Comment> getComments(String slug) {
        var articleEntity = articleRepository.findBySlug(slug);
        return CommentMapper.toArray(articleEntity.getComments());
    }

    public Comment getAuthorCommentById(Long commentId, String authorName) throws BadRequestException {
        var commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException(""));
        validateAuthor(authorName, commentEntity.getAuthor());

        return CommentMapper.fromEntity(commentEntity);
    }

    private void validateAuthor(String authorName, UserEntity author) throws BadRequestException {
        if (!authorName.equals(author.getUserName())) {
            throw new BadRequestException(" ");
        }
    }
}
