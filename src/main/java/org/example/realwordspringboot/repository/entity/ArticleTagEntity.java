package org.example.realwordspringboot.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "article_tag")
@Builder
@Getter
@AllArgsConstructor
public class ArticleTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ArticleEntity article;

    @ManyToOne
    private TagEntity tag;
}
