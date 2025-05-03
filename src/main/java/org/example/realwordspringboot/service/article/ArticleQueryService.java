package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.mapper.ArticleMapper;
import org.example.realwordspringboot.repository.ArticleRepository;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleQueryService {

    private final ArticleRepository articleRepository;

    public Article getArticleBySlug(String slug) {
        var articleEntity = articleRepository.findBySlug(slug);
        return ArticleMapper.fromEntity(articleEntity);
    }

    public Article getAuthorArticleBySlug(String slug, String authorName) throws BadRequestException {
        var articleEntity = articleRepository.findBySlug(slug);
        validateAuthor(authorName, articleEntity.getAuthor());

        return ArticleMapper.fromEntity(articleEntity);
    }


    private void validateAuthor(String authorName, UserEntity author) throws BadRequestException {
        if (!authorName.equals(author.getUserName())) {
            throw new BadRequestException(" ");
        }
    }
}
