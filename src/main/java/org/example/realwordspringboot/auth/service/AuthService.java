package org.example.realwordspringboot.auth.service;

import org.apache.coyote.BadRequestException;

public interface AuthService {

    String createToken(String userName);
    String getUserNameFromToken(String token) throws BadRequestException;
    String getUserNameFromTokenOptional(String token);
}
