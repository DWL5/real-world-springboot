package org.example.realwordspringboot.service.user;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.user.User;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User register(User user) {
        var entity = UserEntity.builder()
                .bio(user.getBio())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .image(user.getImage())
                .build();
        UserEntity saved = userRepository.save(entity);

        return User.builder()
                .bio(saved.getBio())
                .userName(saved.getUserName())
                .password(saved.getPassword())
                .email(saved.getEmail())
                .image(saved.getImage())
                .build();
    }

    @Override
    public User login(String email, String password) throws BadRequestException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(""));

        return User.builder()
                .bio(user.getBio())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .image(user.getImage())
                .build();
    }

    @Override
    public User findUser(String userName) throws BadRequestException {
        var user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));
        return User.builder()
                .bio(user.getBio())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .image(user.getImage())
                .build();
    }
}
