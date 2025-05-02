package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.response.CommentSingleResponse;
import org.example.realwordspringboot.domain.article.Comment;

public class CommentResponseMapper {
    public static CommentSingleResponse from(Comment comment) {
        var author = comment.getAuthor();
        var authorResponse = CommentSingleResponse.AuthorResponse.builder()
                .userName(author.getUserName())
                .bio(author.getBio())
                .image(author.getImage())
                .following(author.isFollowing())
                .build();

        var commentResponse = CommentSingleResponse.CommentResponse.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .author(authorResponse)
                .build();

        return CommentSingleResponse.builder()
                .comment(commentResponse)
                .build();
    }
}
