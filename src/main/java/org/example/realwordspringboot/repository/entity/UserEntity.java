package org.example.realwordspringboot.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String bio;

    private String image;

    @OneToMany(mappedBy = "followerUser", fetch = FetchType.EAGER)
    private List<FollowEntity> followings = new ArrayList<>();

    @OneToMany(mappedBy = "followingUser", fetch = FetchType.EAGER)
    private List<FollowEntity> followers = new ArrayList<>();

    public UserEntity() {
    }
}
