package org.example.realwordspringboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import org.example.realwordspringboot.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class User {
    private String email;
    private String userName;
    private String password;
    private String bio;
    private String image;
    private List<String> followings;
    private List<String> followers;

    public void changeEmail(String email) {
        if (Objects.isNull(email)) {
            return;
        }

        this.email = email;
    }

    public void changeBio(String bio) {
        if (Objects.isNull(bio)) {
            return;
        }

        this.bio = bio;
    }

    public void changeImage(String image) {
        if (Objects.isNull(image)) {
            return;
        }

        this.image = image;
    }

    public boolean isFollowing(String userName) {
        return followings.stream()
                .anyMatch(following -> following.equals(userName));
    }

    public void follow(String following) {
        if (followings.contains(following)) {
            return;
        }

        followings.add(following);
    }

    public void unFollow(String following) {
        if (!followings.contains(following)) {
            return;
        }

        followings.remove(following);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
