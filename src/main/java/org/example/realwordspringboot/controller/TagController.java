package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.controller.dto.response.TagMultipleResponse;
import org.example.realwordspringboot.service.tag.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public TagMultipleResponse tags() {
        var tags = tagService.getTags();

        return TagMultipleResponse.builder()
                .tags(tags)
                .build();
    }
}
