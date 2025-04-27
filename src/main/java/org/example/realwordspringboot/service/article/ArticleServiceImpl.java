package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.domain.dto.ArticleDeleteDto;
import org.example.realwordspringboot.domain.dto.ArticleUpdateDto;
import org.example.realwordspringboot.mapper.ArticleCreateCommandMapper;
import org.example.realwordspringboot.mapper.ArticleMapper;
import org.example.realwordspringboot.mapper.ArticleUpdateCommandMapper;
import org.example.realwordspringboot.mapper.UserMapper;
import org.example.realwordspringboot.repository.ArticleRepository;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleCommandService articleCommandService;


    @Override
    public Article create(ArticleCreateDto articleCreateDto) throws BadRequestException {
        var authorEntity = userRepository.findById(articleCreateDto.authorId())
                .orElseThrow(() -> new BadRequestException(""));

        var author = UserMapper.fromEntity(authorEntity);
        var article = ArticleMapper.fromCreateDto(articleCreateDto, author);
        var command = ArticleCreateCommandMapper.from(article, authorEntity);
        articleCommandService.save(command);
        return article;
    }

    @Override
    public Article update(ArticleUpdateDto articleUpdateDto) throws BadRequestException {
        var articleEntity = articleRepository.findBySlug(articleUpdateDto.slug());
        validateAuthor(articleUpdateDto.authorId(), articleEntity.getAuthor());

        var article = ArticleMapper.fromEntity(articleEntity);
        article.update(articleUpdateDto);

        var command = ArticleUpdateCommandMapper.from(article, articleEntity);
        articleCommandService.update(command);

        return article;
    }

    @Override
    public void delete(ArticleDeleteDto articleDeleteDto) throws BadRequestException {
        var articleEntity = articleRepository.findBySlug(articleDeleteDto.slug());
        validateAuthor(articleDeleteDto.authorId(), articleEntity.getAuthor());

        articleRepository.delete(articleEntity);
    }

    private void validateAuthor(Long authorId, UserEntity author) throws BadRequestException {
        if (!authorId.equals(author.getId())) {
            throw new BadRequestException(" ");
        }
    }
}
