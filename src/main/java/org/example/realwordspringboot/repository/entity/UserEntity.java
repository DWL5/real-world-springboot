package org.example.realwordspringboot.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String bio;

    private String image;

    @OneToMany(mappedBy = "followerUser")
    private List<FollowEntity> followings = new ArrayList<>();

    @OneToMany(mappedBy = "followingUser")
    private List<FollowEntity> followers = new ArrayList<>();

    public UserEntity() {
    }
}
