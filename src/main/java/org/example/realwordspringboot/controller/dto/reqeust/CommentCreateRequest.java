package org.example.realwordspringboot.controller.dto.reqeust;

public record CommentCreateRequest(
        CommentRequest comment
) {

    public record CommentRequest(
            String body
    ) {

    }
}
