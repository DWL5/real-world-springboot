package org.example.realwordspringboot.controller.dto.reqeust;

public record ArticleRequest(
        String tag,
        String author,
        String favorited
) {
}
