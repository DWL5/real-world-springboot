package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.controller.dto.reqeust.RegistrationRequest;
import org.example.realwordspringboot.controller.dto.response.UserResponse;
import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse register(@RequestBody RegistrationRequest request) {
        var user = request.user();
        var registeredUser = userService.register(User.register(user.email(), user.username(), user.password()));
        return new UserResponse(registeredUser.getEmail(), "", registeredUser.getUserName(), registeredUser.getBio(), registeredUser.getImage());
    }
}
