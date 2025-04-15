package org.example.realwordspringboot.repository.dto;

import org.example.realwordspringboot.repository.entity.UserEntity;

public record FollowCommand(
        UserEntity followerEntity,
        UserEntity followingEntity
) {
}
