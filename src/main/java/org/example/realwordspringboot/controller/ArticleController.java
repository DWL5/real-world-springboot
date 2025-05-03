package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.service.AuthService;
import org.example.realwordspringboot.controller.dto.reqeust.ArticleCreateRequest;
import org.example.realwordspringboot.controller.dto.reqeust.ArticleUpdateRequest;
import org.example.realwordspringboot.controller.dto.reqeust.CommentCreateRequest;
import org.example.realwordspringboot.controller.dto.response.ArticleSingleResponse;
import org.example.realwordspringboot.controller.dto.response.CommentMultipleResponse;
import org.example.realwordspringboot.controller.dto.response.CommentSingleResponse;
import org.example.realwordspringboot.domain.dto.ArticleDeleteDto;
import org.example.realwordspringboot.domain.dto.CommentCreateDto;
import org.example.realwordspringboot.domain.dto.CommentDeleteDto;
import org.example.realwordspringboot.mapper.*;
import org.example.realwordspringboot.service.article.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final AuthService authService;

    @PostMapping()
    public ArticleSingleResponse create(@RequestHeader("Authorization") String authorizationHeader,
                                        @RequestBody ArticleCreateRequest request) throws BadRequestException {
        var authorName = authService.getUserNameFromToken(authorizationHeader);
        var article = articleService.create(ArticleCreateDtoMapper.of(request, authorName));
        return ArticleResponseMapper.from(article, authorName);
    }

    @PutMapping("/{slug}")
    public ArticleSingleResponse update(@RequestHeader("Authorization") String authorizationHeader,
                                  @RequestBody ArticleUpdateRequest request, @PathVariable String slug) throws BadRequestException {
        var authorName = authService.getUserNameFromToken(authorizationHeader);
        var article = articleService.update(ArticleUpdateDtoMapper.of(request, authorName, slug));
        return ArticleResponseMapper.from(article, authorName);
    }

    @DeleteMapping("/{slug}")
    public void delete(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String slug) throws BadRequestException {
        var authorName = authService.getUserNameFromToken(authorizationHeader);
        articleService.delete(new ArticleDeleteDto(slug, authorName));
    }

    @PostMapping("/{slug}/comments")
    public CommentSingleResponse comment(@RequestHeader("Authorization") String authorizationHeader,
                                                         @RequestBody CommentCreateRequest request, @PathVariable String slug) throws BadRequestException {
        var authorName = authService.getUserNameFromToken(authorizationHeader);
        var comment = articleService.createComment(new CommentCreateDto(request.comment().body(), authorName, slug));
        return CommentResponseMapper.from(comment);
    }

    @GetMapping("/{slug}/comments")
    public CommentMultipleResponse getComments(@PathVariable String slug) throws BadRequestException {
        var comments = articleService.getComments(slug);
        return CommentResponseMapper.from(comments);
    }

    @DeleteMapping("{slug}/comments/{id}")
    public void deleteComments(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String slug, @PathVariable Long id) throws BadRequestException {
        var authorName = authService.getUserNameFromToken(authorizationHeader);
        articleService.deleteComments(new CommentDeleteDto(authorName, id, slug));
    }

    @PostMapping("{slug}/favorite")
    public ArticleSingleResponse favoriteArticle(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String slug) throws BadRequestException {
        var userName = authService.getUserNameFromToken(authorizationHeader);
        var article = articleService.favoriteArticle(userName, slug);
        return ArticleResponseMapper.from(article, userName);
    }
}
