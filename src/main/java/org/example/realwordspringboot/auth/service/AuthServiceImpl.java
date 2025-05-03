package org.example.realwordspringboot.auth.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.JwtProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final static String NON_AUTH = "0000000000";
    private final JwtProvider jwtProvider;

    @Override
    public String createToken(String userId) {
        return jwtProvider.createToken(userId);
    }

    @Override
    public String getUserNameFromToken(String bearerToken) throws BadRequestException {
        String token = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
        if (jwtProvider.validateToken(token)) {
            return jwtProvider.getUserName(token);
        }

        throw new BadRequestException("");
    }

    @Override
    public String getUserNameFromTokenOptional(String bearerToken) {
        String token = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
        if (jwtProvider.validateToken(token)) {
            return jwtProvider.getUserName(token);
        }

        return NON_AUTH;
    }
}
