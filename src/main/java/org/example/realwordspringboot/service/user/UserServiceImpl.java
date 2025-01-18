package org.example.realwordspringboot.service.user;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.mapper.UserEntityMapper;
import org.example.realwordspringboot.mapper.UserMapper;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User register(User user) {
        var entity = UserEntityMapper.from(user);
        UserEntity saved = userRepository.save(entity);

        return UserMapper.fromEntity(saved);
    }

    @Override
    public User login(String email, String password) throws BadRequestException {
        var userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(""));

        return UserMapper.fromEntity(userEntity);
    }

    @Override
    public User findUser(String userName) throws BadRequestException {
        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));
        return UserMapper.fromEntity(userEntity);
    }

    @Override
    public User updateUser(String userName, String email, String image, String bio) throws BadRequestException {
        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));

        var user = UserMapper.fromEntity(userEntity);
        user.changeEmail(email);
        user.changeBio(bio);
        user.changeImage(image);

        var updated = UserEntityMapper.from(user);
        UserEntity updatedEntity = userRepository.save(updated);

        return UserMapper.fromEntity(updatedEntity);
    }
}
