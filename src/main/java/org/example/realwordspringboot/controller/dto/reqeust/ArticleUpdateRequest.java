package org.example.realwordspringboot.controller.dto.reqeust;

public record ArticleUpdateRequest(
       Article article
) {

    public record Article( String title,
                           String description,
                           String body) {

    }
}
