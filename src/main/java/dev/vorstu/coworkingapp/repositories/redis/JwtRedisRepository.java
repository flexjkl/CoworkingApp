package dev.vorstu.coworkingapp.repositories.redis;

import java.util.Optional;

public interface JwtRedisRepository {
    void addRefreshToken(String username, String refreshToken);
    String findRefreshTokenByUsername(String username);
    void deleteRefreshToken(String username);
}
