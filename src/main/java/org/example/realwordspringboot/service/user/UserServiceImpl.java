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
        var userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(""));

        return User.builder()
                .bio(userEntity.getBio())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .image(userEntity.getImage())
                .build();
    }

    @Override
    public User findUser(String userName) throws BadRequestException {
        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));
        return User.builder()
                .bio(userEntity.getBio())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .image(userEntity.getImage())
                .build();
    }

    @Override
    public User updateUser(String userName, String email, String image, String bio) throws BadRequestException {
        var userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException(""));

        var user = User.builder()
                .bio(userEntity.getBio())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .image(userEntity.getImage())
                .build();

        user.changeEmail(email);
        user.changeBio(bio);
        user.changeImage(image);

        var updated = UserEntity.builder()
                .id(userEntity.getId())
                .bio(user.getBio())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .image(user.getImage())
                .build();

        UserEntity updatedEntity = userRepository.save(updated);
        return User.builder()
                .bio(updatedEntity.getBio())
                .userName(updatedEntity.getUserName())
                .password(updatedEntity.getPassword())
                .email(updatedEntity.getEmail())
                .image(updatedEntity.getImage())
                .build();
    }
}
