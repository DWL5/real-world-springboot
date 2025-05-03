package org.example.realwordspringboot.domain.article;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Author {
    private String userName;
    private String bio;
    private String image;
    private boolean following; //TODO : 구현필요
}
