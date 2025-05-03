package org.example.realwordspringboot.service.user;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.service.AuthService;
import org.example.realwordspringboot.controller.dto.response.UserResponse;
import org.example.realwordspringboot.domain.dto.LoginDto;
import org.example.realwordspringboot.domain.dto.RegistrationDto;
import org.example.realwordspringboot.domain.dto.UserUpdateDto;
import org.example.realwordspringboot.mapper.UserEntityMapper;
import org.example.realwordspringboot.mapper.UserMapper;
import org.example.realwordspringboot.mapper.UserResponseMapper;
import org.example.realwordspringboot.mapper.UserUpdateCommandMapper;
import org.example.realwordspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCommandService userCommandService;
    private final AuthService authService;

    public UserResponse register(RegistrationDto dto) {
        var entity = UserEntityMapper.from(dto);
        var saved = userRepository.save(entity);
        var user = UserMapper.fromEntity(saved);
        var token = authService.createToken(saved.getUserName());

        return UserResponseMapper.toUserResponse(token, user);
    }

    @Override
    public UserResponse login(LoginDto dto) throws BadRequestException {
        var userEntity = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new BadRequestException(""));

        var user = UserMapper.fromEntity(userEntity);
        var token = authService.createToken(userEntity.getUserName());
        return UserResponseMapper.toUserResponse(token, user);
    }

    @Override
    public UserResponse findUser(String userName) throws BadRequestException {
        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));

        var user = UserMapper.fromEntity(userEntity);
        var token = authService.createToken(userEntity.getUserName());
        return UserResponseMapper.toUserResponse(token, user);
    }

    @Override
    public UserResponse updateUser(UserUpdateDto dto) throws BadRequestException {
        var userEntity = userRepository.findByUserName(dto.userName())
                .orElseThrow(() -> new BadRequestException(""));

        var user = UserMapper.fromEntity(userEntity);
        user.changeEmail(dto.email());
        user.changeBio(dto.bio());
        user.changeImage(dto.image());

        var updated = userCommandService.update(UserUpdateCommandMapper.of(user, userEntity));
        var token = authService.createToken(updated.getUserName());
        return UserResponseMapper.toUserResponse(token, user);
    }
}
