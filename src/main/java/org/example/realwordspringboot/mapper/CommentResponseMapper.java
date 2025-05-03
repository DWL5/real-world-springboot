package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.controller.dto.response.CommentMultipleResponse;
import org.example.realwordspringboot.controller.dto.response.CommentSingleResponse;
import org.example.realwordspringboot.domain.article.Comment;

import java.util.List;

public class CommentResponseMapper {
    public static CommentSingleResponse from(Comment comment) {
        var commentResponse = toCommentResponse(comment);

        return CommentSingleResponse.builder()
                .comment(commentResponse)
                .build();
    }


    public static CommentMultipleResponse from(List<Comment> comments) {
        var commentResponses = comments.stream()
                .map(CommentResponseMapper::toCommentResponse)
                .toList();

        return CommentMultipleResponse.builder()
                .comments(commentResponses)
                .build();
    }


    private static CommentSingleResponse.CommentResponse toCommentResponse(Comment comment) {
        var author = comment.getAuthor();
        var authorResponse = CommentSingleResponse.AuthorResponse.builder()
                .userName(author.getUserName())
                .bio(author.getBio())
                .image(author.getImage())
                .following(author.isFollowing())
                .build();

        return CommentSingleResponse.CommentResponse.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .author(authorResponse)
                .build();

    }
}
