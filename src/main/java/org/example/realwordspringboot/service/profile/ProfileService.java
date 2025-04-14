package org.example.realwordspringboot.service.profile;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.profile.Profile;

public interface ProfileService {
    Profile getProfile(Long authUser, String profileUser) throws BadRequestException;
    Profile follow(Long follower, String followee) throws BadRequestException;
}
