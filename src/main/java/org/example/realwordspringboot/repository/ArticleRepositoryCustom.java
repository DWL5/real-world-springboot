package org.example.realwordspringboot.repository;

import org.example.realwordspringboot.domain.dto.ArticleConditionDto;
import org.example.realwordspringboot.repository.entity.ArticleEntity;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleEntity> search(ArticleConditionDto articleConditionDto);
}
