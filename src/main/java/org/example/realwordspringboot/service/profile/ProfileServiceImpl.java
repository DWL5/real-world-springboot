package org.example.realwordspringboot.service.profile;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.profile.Profile;
import org.example.realwordspringboot.mapper.UserMapper;
import org.example.realwordspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final static String NON_AUTH = "";
    private final UserRepository userRepository;

    @Override
    public Profile getProfile(String authUserName, String profileUserName) throws BadRequestException {
        var profileUserEntity = userRepository.findByUserName(profileUserName)
                .orElseThrow(() -> new BadRequestException(""));
        var profileUser = UserMapper.fromEntity(profileUserEntity);

        return Profile.of(profileUser, isFollowing(authUserName, profileUser.getUserName()));
    }

    private boolean isFollowing(String authUserName, String profileUserName) throws BadRequestException {
        if (NON_AUTH.equals(authUserName)) {
            return false;
        }

        var authUserEntity = userRepository.findByUserName(authUserName)
                .orElseThrow(() -> new BadRequestException(""));
        var authUser = UserMapper.fromEntity(authUserEntity);

        return authUser.isFollowing(profileUserName);
    }
}
