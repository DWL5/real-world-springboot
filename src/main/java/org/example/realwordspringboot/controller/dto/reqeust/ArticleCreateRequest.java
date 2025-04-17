package org.example.realwordspringboot.controller.dto.reqeust;

import java.util.List;

public record ArticleCreateRequest(
       Article article
) {

    public record Article( String title,
                           String description,
                           String body,
                           List<String> tagList) {

    }
}
