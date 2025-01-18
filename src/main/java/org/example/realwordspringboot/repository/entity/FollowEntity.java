package org.example.realwordspringboot.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "follow")
@Builder
@Getter
@AllArgsConstructor
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    private UserEntity followerUser;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private UserEntity followingUser;

    private LocalDateTime followDate;

    public FollowEntity() {
    }
}

