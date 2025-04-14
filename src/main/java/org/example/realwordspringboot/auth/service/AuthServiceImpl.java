package org.example.realwordspringboot.auth.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.realwordspringboot.auth.JwtProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final static Long NON_AUTH = 0L;
    private final JwtProvider jwtProvider;

    public String createToken(Long userId) {
        return jwtProvider.createToken(userId);
    }

    public Long getUserIdFromToken(String bearerToken) throws BadRequestException {
        String token = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
        if (jwtProvider.validateToken(token)) {
            return jwtProvider.getUserId(token);
        }

        throw new BadRequestException("");
    }

    @Override
    public Long getUserIdFromTokenOptional(String bearerToken) {
        String token = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
        if (jwtProvider.validateToken(token)) {
            return jwtProvider.getUserId(token);
        }

        return NON_AUTH;
    }
}
