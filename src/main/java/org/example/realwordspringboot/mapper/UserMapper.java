package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.entity.UserEntity;

public class UserMapper {
    public static User fromEntity(UserEntity userEntity) {
        var followers = userEntity.getFollowers().stream()
                .map(followerEntity -> fromEntity(followerEntity.getFollowerUser()))
                .toList();

        var followings = userEntity.getFollowings().stream()
                .map(followEntity -> fromEntity(followEntity.getFollowerUser()))
                .toList();

        return User.builder()
                .bio(userEntity.getBio())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .image(userEntity.getImage())
                .followers(followers)
                .followings(followings)
                .build();
    }
}
