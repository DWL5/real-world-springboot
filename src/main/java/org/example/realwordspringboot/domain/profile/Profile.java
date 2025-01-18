package org.example.realwordspringboot.domain.profile;

import lombok.Builder;
import lombok.Getter;
import org.example.realwordspringboot.domain.user.User;

@Builder
@Getter
public class Profile {
    private String userName;
    private String bio;
    private String image;
    private boolean following;

    public static Profile of(User profileUser, boolean isFollowing) {
        return Profile.builder()
                .userName(profileUser.getUserName())
                .bio(profileUser.getBio())
                .image(profileUser.getImage())
                .following(isFollowing)
                .build();
    }
}
