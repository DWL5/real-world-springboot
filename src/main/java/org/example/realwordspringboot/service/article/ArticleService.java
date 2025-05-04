package org.example.realwordspringboot.service.article;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.domain.dto.*;

import java.util.List;
import java.util.Set;

public interface ArticleService {
    Set<Article> getArticles(ArticleConditionDto conditions);
    Article create(ArticleCreateDto articleCreateDto) throws BadRequestException;
    Article update(ArticleUpdateDto articleUpdateDto) throws BadRequestException;
    void delete(ArticleDeleteDto articleDeleteDto) throws BadRequestException;
    Comment createComment(CommentCreateDto commentCreateDto) throws BadRequestException;
    List<Comment> getComments(String slug) throws BadRequestException;
    void deleteComments(CommentDeleteDto commentDeleteDto) throws BadRequestException;
    Article favoriteArticle(String  userName, String slug) throws BadRequestException;
    Article unFavoriteArticle(String  userName, String slug) throws BadRequestException;
}
