package org.example.realwordspringboot.service.profile;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.profile.Profile;
import org.example.realwordspringboot.mapper.UserMapper;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.dto.FollowCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final static String NON_AUTH = "0000000000";
    private final UserRepository userRepository;
    private final ProfileCommandService profileCommandService;

    @Override
    public Profile getProfile(String authUserId, String profileUserName) throws BadRequestException {
        var profileUserEntity = userRepository.findByUserName(profileUserName)
                .orElseThrow(() -> new BadRequestException(""));
        var profileUser = UserMapper.fromEntity(profileUserEntity);

        return Profile.of(profileUser, isFollowing(authUserId, profileUserName));
    }

    @Override
    public Profile follow(String userName, String followingName) throws BadRequestException {
        var followerEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));

        var follower = UserMapper.fromEntity(followerEntity);

        var followingEntity = userRepository.findByUserName(followingName)
                .orElseThrow(() -> new BadRequestException(""));

        var following = UserMapper.fromEntity(followingEntity);

        follower.follow(followingName);
        profileCommandService.follow(new FollowCommand(followerEntity, followingEntity));

        return Profile.of(following, follower.isFollowing(followingName));
    }

    @Override
    public Profile unFollow(String userName, String followingName) throws BadRequestException {
        var followerEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));

        var follower = UserMapper.fromEntity(followerEntity);

        var followingEntity = userRepository.findByUserName(followingName)
                .orElseThrow(() -> new BadRequestException(""));

        var following = UserMapper.fromEntity(followingEntity);

        follower.unFollow(followingName);
        profileCommandService.unFollow(new FollowCommand(followerEntity, followingEntity));

        return Profile.of(following, follower.isFollowing(followingName));
    }

    private boolean isFollowing(String userName, String profileUserName) throws BadRequestException {
        if (NON_AUTH.equals(userName)) {
            return false;
        }

        var authUserEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));
        var authUser = UserMapper.fromEntity(authUserEntity);

        return authUser.isFollowing(profileUserName);
    }
}
