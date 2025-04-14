package org.example.realwordspringboot.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.realwordspringboot.domain.dto.UserUpdateDto;
import org.example.realwordspringboot.repository.UserRepository;
import org.example.realwordspringboot.repository.dto.UserUpdateCommand;
import org.example.realwordspringboot.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;

    public UserEntity update(UserUpdateCommand command) {
        var entity = command.userEntity();
        entity.setBio(command.bio());
        entity.setImage(command.image());
        entity.setEmail(command.email());

        return userRepository.save(entity);
    }
}
