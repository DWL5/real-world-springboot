package org.example.realwordspringboot.repository;

import org.example.realwordspringboot.repository.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>, ArticleRepositoryCustom {

    ArticleEntity findBySlug(String slug);
    void deleteBySlug(String slug);
}
