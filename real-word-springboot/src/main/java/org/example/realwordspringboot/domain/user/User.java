package org.example.realwordspringboot.domain.user;

import lombok.Builder;
import lombok.Getter;

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
}
