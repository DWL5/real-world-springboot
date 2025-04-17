package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Article;
import org.example.realwordspringboot.domain.dto.ArticleCreateDto;
import org.example.realwordspringboot.mapper.ArticleCreateCommandMapper;
import org.example.realwordspringboot.mapper.ArticleMapper;
import org.example.realwordspringboot.mapper.UserMapper;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.dto.ArticleCreateCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final UserRepository userRepository;
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
}
