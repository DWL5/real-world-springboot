package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserMapper {
    public static User fromEntity(UserEntity userEntity) {
        var followers = userEntity.getFollowers().stream()
                .map(followerEntity -> followerEntity.getFollowerUser().getUserName())
                .collect(Collectors.toCollection(ArrayList::new));

        var followings = userEntity.getFollowings().stream()
                .map(followEntity -> followEntity.getFollowerUser().getUserName())
                .collect(Collectors.toCollection(ArrayList::new));

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
