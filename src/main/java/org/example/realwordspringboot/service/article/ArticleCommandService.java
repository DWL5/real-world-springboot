package org.example.realwordspringboot.service.article;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.repository.ArticleRepository;
import org.example.realwordspringboot.repository.TagRepository;
import org.example.realwordspringboot.repository.dto.ArticleCreateCommand;
import org.example.realwordspringboot.repository.dto.ArticleUpdateCommand;
import org.example.realwordspringboot.repository.entity.ArticleEntity;
import org.example.realwordspringboot.repository.entity.ArticleTagEntity;
import org.example.realwordspringboot.repository.entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandService {
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    public ArticleEntity save(ArticleCreateCommand command) {
        var articleEntity =  ArticleEntity.builder()
                .title(command.title())
                .slug(command.slug())
                .body(command.body())
                .description(command.description())
                .author(command.authorEntity())
                .createdAt(command.createdAt())
                .updatedAt(command.updateAt())
                .build();

        var tags = buildTagEntities(articleEntity, command.tagList());

        articleEntity.getArticleTags().addAll(tags);
        return articleRepository.save(articleEntity);
    }

    public void update(ArticleUpdateCommand command) {
        var entity = command.articleEntity();
        entity.setTitle(command.title());
        entity.setSlug(command.slug());
        entity.setBody(command.body());
        entity.setDescription(command.description());
        entity.setUpdatedAt(command.updateAt());
    }

    private List<ArticleTagEntity> buildTagEntities(ArticleEntity article, List<String> tagList) {
        return tagList.stream()
                .map(tagName -> {
                    TagEntity tag = tagRepository.findByName(tagName)
                            .orElseGet(() -> tagRepository.save(
                                    TagEntity.builder().name(tagName).build()));

                    return ArticleTagEntity.builder()
                            .article(article)
                            .tag(tag)
                            .build();
                }).toList();
    }
}
