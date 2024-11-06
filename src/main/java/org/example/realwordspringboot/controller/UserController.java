package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.JwtProvider;
import org.example.realwordspringboot.controller.dto.reqeust.LoginRequest;
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
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @PostMapping
    public UserResponse register(@RequestBody RegistrationRequest request) {
        var requestUser = request.user();
        var user = userService.register(User.register(requestUser.email(), requestUser.username(), requestUser.password()));
        var token = jwtProvider.createToken(user.getUserName());
        return new UserResponse(user.getEmail(), token, user.getUserName(), user.getBio(), user.getImage());
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) throws BadRequestException {
        var requestUser = request.user();
        var user = userService.login(requestUser.email(), requestUser.password());
        var token = jwtProvider.createToken(user.getUserName());
        return new UserResponse(user.getEmail(), token, user.getUserName(), user.getBio(), user.getImage());
    }
}
