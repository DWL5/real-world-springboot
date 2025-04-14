package org.example.realwordspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.service.AuthService;
import org.example.realwordspringboot.controller.dto.reqeust.LoginRequest;
import org.example.realwordspringboot.controller.dto.reqeust.RegistrationRequest;
import org.example.realwordspringboot.controller.dto.reqeust.UpdateUserRequest;
import org.example.realwordspringboot.controller.dto.response.UserResponse;
import org.example.realwordspringboot.mapper.UserUpdateDtoMapper;
import org.example.realwordspringboot.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping
    public UserResponse register(@RequestBody RegistrationRequest request) {
        return userService.register(request.toRegistrationDto());
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) throws BadRequestException {
        return userService.login(request.toLoginDto());
    }

    @GetMapping
    public UserResponse getUser(@RequestHeader("Authorization") String authorizationHeader) throws BadRequestException {
        var userId = authService.getUserIdFromToken(authorizationHeader);
        return userService.findUser(userId);
    }

    @PutMapping
    public UserResponse updateUser(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateUserRequest request)
            throws BadRequestException {
        var userId = authService.getUserIdFromToken(authorizationHeader);
        var updateRequestedUser = request.user();
        var command = UserUpdateDtoMapper.of(updateRequestedUser, userId);
        return userService.updateUser(command);
    }
}
