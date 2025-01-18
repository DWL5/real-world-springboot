package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.service.AuthService;
import org.example.realwordspringboot.controller.dto.response.ProfileResponse;
import org.example.realwordspringboot.service.profile.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final AuthService authService;

    @GetMapping("/{userName}")
    public ProfileResponse getProfile(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String userName) throws BadRequestException {
        var authUserName = authService.getUserNameFromTokenOptional(authorizationHeader);
        var profile = profileService.getProfile(authUserName, userName);
        return new ProfileResponse(profile.getUserName(), profile.getBio(), profile.getBio(), profile.isFollowing());
    }
}
