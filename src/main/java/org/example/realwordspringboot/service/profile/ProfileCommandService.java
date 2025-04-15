package org.example.realwordspringboot.service.profile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.repository.FollowRepository;
import org.example.realwordspringboot.repository.dto.FollowCommand;
import org.example.realwordspringboot.repository.entity.FollowEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileCommandService {
    private final FollowRepository followRepository;

    public void follow(FollowCommand followCommand) {
        var following = followCommand.followingEntity();
        var follower = followCommand.followerEntity();

        FollowEntity follow = FollowEntity.builder()
                .followerUser(follower)
                .followingUser(following)
                .build();

        followRepository.save(follow);
    }

    public void unFollow(FollowCommand followCommand) {
        var following = followCommand.followingEntity();
        var follower = followCommand.followerEntity();
        followRepository.deleteFollowerUserByFollowerUserAndFollowingUser(follower, following);
    }
}
