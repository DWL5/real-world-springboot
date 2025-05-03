package org.example.realwordspringboot.domain.article;

import lombok.Builder;
import lombok.Getter;
import org.example.realwordspringboot.domain.dto.CommentCreateDto;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
public class Comment {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String body;
    private Author author;


    public static Comment create(CommentCreateDto commentCreateDto, Author author) {
        return Comment.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .body(commentCreateDto.body())
                .author(author)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
