package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.repository.ArticleRepository;
import org.example.realwordspringboot.repository.FavoriteRepository;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.entity.FavoriteEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteCommandService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final FavoriteRepository favoriteRepository;

    public void save(String userName, String slug) throws BadRequestException {

        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));
        var articleEntity = articleRepository.findBySlug(slug);

        var favoriteEntity = FavoriteEntity.builder()
                .user(userEntity)
                .article(articleEntity)
                .build();

        favoriteRepository.save(favoriteEntity);
    }

    public void delete(String userName, String slug) throws BadRequestException {

        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));
        var articleEntity = articleRepository.findBySlug(slug);

        var favoriteEntity = FavoriteEntity.builder()
                .user(userEntity)
                .article(articleEntity)
                .build();

        favoriteRepository.delete(favoriteEntity);
    }
}
