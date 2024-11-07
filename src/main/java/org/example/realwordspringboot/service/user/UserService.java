package org.example.realwordspringboot.service.user;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.user.User;

public interface UserService {
    User register(User user);
    User login(String email, String password) throws BadRequestException;
    User findUser(String userName) throws BadRequestException;
}
