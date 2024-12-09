package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.controller.dto.response.ProfileResponse;
import org.example.realwordspringboot.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping("/{userName}")
    public ProfileResponse getProfile(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String userName) throws BadRequestException {
        var user = userService.findUser(userName);
        return null;
    }
}
