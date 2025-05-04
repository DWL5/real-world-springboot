package org.example.realwordspringboot.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.domain.dto.ArticleConditionDto;
import org.example.realwordspringboot.repository.entity.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private static final QArticleEntity article = QArticleEntity.articleEntity;
    private static final QArticleTagEntity articleTag = QArticleTagEntity.articleTagEntity;
    private static final QFavoriteEntity favorite = QFavoriteEntity.favoriteEntity;
    private static final QFollowEntity follow = QFollowEntity.followEntity;

    @Override
    public List<ArticleEntity> search(ArticleConditionDto condition) {

        var query = queryFactory
                .selectFrom(article)
                .distinct();

        // 동적 조인
        if (condition.tagName() != null) {
            query.leftJoin(article.articleTags, articleTag).fetchJoin();
        }

        if (condition.favoritedUserName() != null) {
            query.leftJoin(article.favorites, favorite).fetchJoin();
        }

        // 조건
        query.where(getConditions(condition));

        // 페이징
        query.offset(condition.offset())
                .limit(condition.limit());

        query.orderBy(article.createdAt.desc());

        return query.fetch();
    }

    @Override
    public List<ArticleEntity> feed(String userName, long offset, long limit) {
        return queryFactory
                .selectFrom(article)
                .join(follow).on(follow.followingUser.id.eq(article.author.id))
                .where(follow.followerUser.userName.eq(userName))
                .offset(offset)
                .limit(limit)
                .orderBy(article.createdAt.desc())
                .fetch();
    }

    private BooleanBuilder getConditions(ArticleConditionDto condition) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (Objects.nonNull(condition.tagName())) {
            booleanBuilder.and(articleTag.tag.name.eq(condition.tagName()));
        }

        if (Objects.nonNull(condition.authorName())) {
            booleanBuilder.and(article.author.userName.eq(condition.authorName()));
        }

        if (Objects.nonNull(condition.favoritedUserName())) {
            booleanBuilder.and(favorite.user.userName.eq(condition.favoritedUserName()));
        }

        return booleanBuilder;
    }
}