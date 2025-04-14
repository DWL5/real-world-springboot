package org.example.realwordspringboot.auth.service;

import org.apache.coyote.BadRequestException;

public interface AuthService {

    String createToken(Long userId);
    Long getUserIdFromToken(String token) throws BadRequestException;
    Long getUserIdFromTokenOptional(String token);
}
