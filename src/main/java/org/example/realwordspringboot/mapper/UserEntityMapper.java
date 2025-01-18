package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.entity.FollowEntity;
import org.example.realwordspringboot.repository.entity.UserEntity;

public class UserEntityMapper {
    public static UserEntity from(User user) {
        var userEntity = from(user);

        var followers = user.getFollowers().stream()
                .map(follower -> of(from(follower), userEntity))
                .toList();

        var followings = user.getFollowings().stream()
                .map(following -> of(from(following), userEntity))
                .toList();

        return UserEntity.builder()
                .bio(user.getBio())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .image(user.getImage())
                .followers(followers)
                .followings(followings)
                .build();
    }

    private static FollowEntity of(UserEntity follower, UserEntity following) {
        return FollowEntity.builder()
                .followerUser(follower)
                .followingUser(following)
                .build();
    }
}
