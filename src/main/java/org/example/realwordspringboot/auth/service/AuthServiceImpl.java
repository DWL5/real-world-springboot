package org.example.realwordspringboot.auth.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.JwtProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final static String NON_AUTH = "";
    private final JwtProvider jwtProvider;

    public String createToken(String userName) {
        return jwtProvider.createToken(userName);
    }

    public String getUserNameFromToken(String bearerToken) throws BadRequestException {
        String token = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
        if (jwtProvider.validateToken(token)) {
            return jwtProvider.getUsername(token);
        }

        throw new BadRequestException("");
    }

    @Override
    public String getUserNameFromTokenOptional(String bearerToken) {
        String token = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
        if (jwtProvider.validateToken(token)) {
            return jwtProvider.getUsername(token);
        }

        return NON_AUTH;
    }
}
