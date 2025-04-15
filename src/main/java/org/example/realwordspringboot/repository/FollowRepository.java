package org.example.realwordspringboot.repository;

import org.example.realwordspringboot.repository.entity.FollowEntity;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

    void deleteFollowerUserByFollowerUserAndFollowingUser(UserEntity followerUser, UserEntity followingUser);
}
