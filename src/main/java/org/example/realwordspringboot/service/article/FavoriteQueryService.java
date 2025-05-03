package org.example.realwordspringboot.service.article;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.article.Favorite;
import org.example.realwordspringboot.mapper.ArticleMapper;
import org.example.realwordspringboot.mapper.UserMapper;
import org.example.realwordspringboot.repository.FavoriteRepository;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.entity.FavoriteEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteQueryService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    public Favorite getFavorite(String userName) throws BadRequestException {
        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));
        var user = UserMapper.fromEntity(userEntity);

        var userFavoriteEntities = favoriteRepository.findAllByUserId(userEntity.getId());
        var articleEntity = userFavoriteEntities.stream()
                .map(FavoriteEntity::getArticle)
                .toList();

        var articles = ArticleMapper.toSet(articleEntity);
        return Favorite.builder()
                .user(user)
                .articles(articles)
                .build();
    }
}
