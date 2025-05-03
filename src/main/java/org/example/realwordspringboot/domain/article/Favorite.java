package org.example.realwordspringboot.domain.article;

import lombok.Builder;
import lombok.Getter;
import org.example.realwordspringboot.domain.user.User;

import java.util.Set;

@Getter
@Builder
public class Favorite {
    private User user;
    private Set<Article> articles;

    public boolean addArticle(Article article) {
        if (articles.contains(article)) {
            return false;
        }

        articles.add(article);
        return true;
    }
}
