package org.example.realwordspringboot.service.article;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.dto.ArticleDeleteDto;
import org.example.realwordspringboot.domain.dto.ArticleUpdateDto;
import org.example.realwordspringboot.domain.dto.CommentCreateDto;

public interface ArticleService {
    Article create(ArticleCreateDto articleCreateDto) throws BadRequestException;
    Article update(ArticleUpdateDto articleUpdateDto) throws BadRequestException;
    void delete(ArticleDeleteDto articleDeleteDto) throws BadRequestException;
    Comment comment(CommentCreateDto commentCreateDto) throws BadRequestException;
}
