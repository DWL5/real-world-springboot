package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.domain.dto.ArticleConditionDto;
import org.example.realwordspringboot.mapper.ArticleMapper;
import org.example.realwordspringboot.mapper.CommentMapper;
import org.example.realwordspringboot.repository.ArticleRepository;
import org.example.realwordspringboot.repository.ArticleRepositoryCustomImpl;
import org.example.realwordspringboot.repository.entity.ArticleEntity;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ArticleRepositoryCustomImpl articleRepositoryImpl;

    public Set<Article> getByConditions(ArticleConditionDto condition) {
        var entities = articleRepositoryImpl.search(condition);
        return ArticleMapper.toSet(entities, condition.viewerName());
    }

    public Set<Article> feed(String viewerName, long offset, long limit) {
        var entities = articleRepositoryImpl.feed(viewerName, offset, limit);
        return ArticleMapper.toSet(entities, viewerName);
    }

    public Article getArticleBySlug(String slug, String viewerName) {
        var articleEntity = articleRepository.findBySlug(slug);
        return ArticleMapper.fromEntity(articleEntity, viewerName);
    }

    public Article getAuthorArticleBySlug(String slug, String authorName, String viewerName) throws BadRequestException {
        var articleEntity = articleRepository.findBySlug(slug);
        validateAuthor(authorName, articleEntity.getAuthor());

        return ArticleMapper.fromEntity(articleEntity, viewerName);
    }

    private void validateAuthor(String authorName, UserEntity author) throws BadRequestException {
        if (!authorName.equals(author.getUserName())) {
            throw new BadRequestException(" ");
        }
    }
}
