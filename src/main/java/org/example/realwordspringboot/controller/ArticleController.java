package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.service.AuthService;
import org.example.realwordspringboot.controller.dto.reqeust.ArticleCreateRequest;
import org.example.realwordspringboot.controller.dto.reqeust.ArticleUpdateRequest;
import org.example.realwordspringboot.controller.dto.response.ArticleResponse;
import org.example.realwordspringboot.domain.dto.ArticleUpdateDto;
import org.example.realwordspringboot.mapper.ArticleCreateDtoMapper;
import org.example.realwordspringboot.mapper.ArticleResponseMapper;
import org.example.realwordspringboot.mapper.ArticleUpdateDtoMapper;
import org.example.realwordspringboot.service.article.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final AuthService authService;

    @PostMapping()
    public ArticleResponse create(@RequestHeader("Authorization") String authorizationHeader,
                                  @RequestBody ArticleCreateRequest request) throws BadRequestException {
        Long authorId = authService.getUserIdFromToken(authorizationHeader);
        var article = articleService.create(ArticleCreateDtoMapper.of(request, authorId));
        return ArticleResponseMapper.from(article);
    }

    @PutMapping("/{slug}")
    public ArticleResponse update(@RequestHeader("Authorization") String authorizationHeader,
                                  @RequestBody ArticleUpdateRequest request, @PathVariable String slug) throws BadRequestException {
        Long authorId = authService.getUserIdFromToken(authorizationHeader);
        var article = articleService.update(ArticleUpdateDtoMapper.of(request, authorId, slug));
        return ArticleResponseMapper.from(article);
    }
}
