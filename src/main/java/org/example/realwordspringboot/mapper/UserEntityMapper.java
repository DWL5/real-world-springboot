package org.example.realwordspringboot.mapper;

import org.example.realwordspringboot.domain.dto.RegistrationDto;
import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.entity.FollowEntity;
import org.example.realwordspringboot.repository.entity.UserEntity;

import java.util.ArrayList;

public class UserEntityMapper {
    public static UserEntity from(RegistrationDto dto) {
        return UserEntity.builder()
                .userName(dto.username())
                .password(dto.password())
                .email(dto.email())
                .followings(new ArrayList<>())
                .followers(new ArrayList<>())
                .build();
    }

    public static UserEntity from(User user) {
        var followers = user.getFollowers().stream()
                .map(follower -> of(from(follower), user))
                .toList();

        var followings = user.getFollowings().stream()
                .map(following -> of(from(following), user))
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

    private static FollowEntity of(UserEntity follower, User user) {
        var following = from(user);
        return FollowEntity.builder()
                .followerUser(follower)
                .followingUser(following)
                .build();
    }
}
