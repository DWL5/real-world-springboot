package org.example.realwordspringboot.auth.service;

import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.domain.user.User;

public interface AuthService {

    String createToken(String userName);
    String getUserNameFromToken(String token) throws BadRequestException;
}
