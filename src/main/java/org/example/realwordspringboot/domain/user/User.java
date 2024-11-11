package org.example.realwordspringboot.domain.user;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
public class User {
    private String email;
    private String userName;
    private String password;
    private String bio;
    private String image;

    public static User register(String email, String userName, String password) {
        return User.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .build();
    }

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
        if (Objects.isNull(email)) {
            return;
        }

        this.image = image;
    }
}
