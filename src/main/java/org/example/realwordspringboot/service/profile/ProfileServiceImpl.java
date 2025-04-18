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
    private final static Long NON_AUTH = 0L;
    private final UserRepository userRepository;
    private final ProfileCommandService profileCommandService;

    @Override
    public Profile getProfile(Long authUserId, String profileUserName) throws BadRequestException {
        var profileUserEntity = userRepository.findByUserName(profileUserName)
                .orElseThrow(() -> new BadRequestException(""));
        var profileUser = UserMapper.fromEntity(profileUserEntity);

        return Profile.of(profileUser, isFollowing(authUserId, profileUserName));
    }

    @Override
    public Profile follow(Long authUserId, String followingName) throws BadRequestException {
        var followerEntity = userRepository.findById(authUserId)
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
    public Profile unFollow(Long authUserId, String followingName) throws BadRequestException {
        var followerEntity = userRepository.findById(authUserId)
                .orElseThrow(() -> new BadRequestException(""));

        var follower = UserMapper.fromEntity(followerEntity);

        var followingEntity = userRepository.findByUserName(followingName)
                .orElseThrow(() -> new BadRequestException(""));

        var following = UserMapper.fromEntity(followingEntity);

        follower.unFollow(followingName);
        profileCommandService.unFollow(new FollowCommand(followerEntity, followingEntity));

        return Profile.of(following, follower.isFollowing(followingName));
    }

    private boolean isFollowing(Long userId, String profileUserName) throws BadRequestException {
        if (NON_AUTH.equals(userId)) {
            return false;
        }

        var authUserEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(""));
        var authUser = UserMapper.fromEntity(authUserEntity);

        return authUser.isFollowing(profileUserName);
    }
}
