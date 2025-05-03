package org.example.realwordspringboot.controller.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CommentMultipleResponse(
        List<CommentSingleResponse.CommentResponse> comments
) {
}
