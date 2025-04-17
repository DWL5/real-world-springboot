package org.example.realwordspringboot.service.article;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;

public interface ArticleService {
    Article create(ArticleCreateDto articleCreateDto) throws BadRequestException;
}
