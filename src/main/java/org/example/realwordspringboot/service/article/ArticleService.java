package org.example.realwordspringboot.service.article;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.dto.ArticleUpdateDto;

public interface ArticleService {
    Article create(ArticleCreateDto articleCreateDto) throws BadRequestException;
    Article update(ArticleUpdateDto articleUpdateDto) throws BadRequestException;
}
