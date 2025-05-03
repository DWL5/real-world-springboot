package org.example.realwordspringboot.service.profile;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.profile.Profile;

public interface ProfileService {
    Profile getProfile(String authUser, String profileUser) throws BadRequestException;
    Profile follow(String follower, String following) throws BadRequestException;
    Profile unFollow(String follower, String following) throws BadRequestException;
}
