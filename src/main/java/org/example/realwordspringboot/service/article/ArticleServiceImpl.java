package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.article.Comment;
import org.example.realwordspringboot.domain.dto.*;
import org.example.realwordspringboot.mapper.*;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.dto.CommentCreateCommand;
import org.example.realwordspringboot.repository.dto.CommentDeleteCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final UserRepository userRepository;

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    private final FavoriteQueryService favoriteQueryService;
    private final FavoriteCommandService favoriteCommandService;


    @Override
    public Article create(ArticleCreateDto articleCreateDto) throws BadRequestException {
        var authorEntity = userRepository.findByUserName(articleCreateDto.authorName())
                .orElseThrow(() -> new BadRequestException(""));

        var author = UserMapper.fromEntity(authorEntity);
        var article = ArticleMapper.fromCreateDto(articleCreateDto, author);
        var command = ArticleCreateCommandMapper.from(article, authorEntity);
        articleCommandService.save(command);
        return article;
    }

    @Override
    public Article update(ArticleUpdateDto articleUpdateDto) throws BadRequestException {
        var article = articleQueryService.getAuthorArticleBySlug(articleUpdateDto.slug(), articleUpdateDto.authorName());
        article.update(articleUpdateDto);

        var command = ArticleUpdateCommandMapper.from(article);
        articleCommandService.update(command);

        return article;
    }

    @Override
    public void delete(ArticleDeleteDto articleDeleteDto) throws BadRequestException {
        var article = articleQueryService.getAuthorArticleBySlug(articleDeleteDto.slug(), articleDeleteDto.authorName());
        articleCommandService.delete(article.getSlug());
    }

    @Override
    public Comment createComment(CommentCreateDto commentCreateDto) throws BadRequestException {
        var authorEntity = userRepository.findByUserName(commentCreateDto.authorName())
                .orElseThrow(() -> new BadRequestException(""));
        var author = UserMapper.fromEntity(authorEntity);
        var comment = CommentMapper.fromCreateDto(commentCreateDto, author);

        var command = new CommentCreateCommand(comment, commentCreateDto.slug(), authorEntity);
        var commentEntity = commentCommandService.save(command);
        return CommentMapper.fromEntity(commentEntity);
    }

    @Override
    public List<Comment> getComments(String slug) {
        return articleQueryService.getArticleBySlug(slug).getComments();
    }

    @Override
    public void deleteComments(CommentDeleteDto commentDeleteDto) throws BadRequestException {

        var article = articleQueryService.getArticleBySlug(commentDeleteDto.slug());
        var comment = commentQueryService.getAuthorCommentById(commentDeleteDto.commentId(), commentDeleteDto.authorName());
        article.deleteComment(comment);

        var command = new CommentDeleteCommand(article, comment);
        commentCommandService.delete(command);
    }

    @Override
    public Article favoriteArticle(String userName, String slug) throws BadRequestException {
        var favorites = favoriteQueryService.getFavorite(userName);
        var article = articleQueryService.getArticleBySlug(slug);

        var added = favorites.addArticle(article);
        if (added) {
            article.favorite(favorites.getUser().getUserName());
            favoriteCommandService.save(userName, slug);
            return article;
        }

        throw new BadRequestException("");
    }

    @Override
    public Article unFavoriteArticle(String userName, String slug) throws BadRequestException {
        var favorites = favoriteQueryService.getFavorite(userName);
        var article = articleQueryService.getArticleBySlug(slug);

        var removed = favorites.removeArticle(article);
        if (removed) {
            article.unFavorite(favorites.getUser().getUserName());
            favoriteCommandService.delete(userName, slug);
        }

        throw new BadRequestException("");
    }
}
