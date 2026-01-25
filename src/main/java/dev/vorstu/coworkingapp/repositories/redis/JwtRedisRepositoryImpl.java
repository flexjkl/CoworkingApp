package dev.vorstu.coworkingapp.repositories.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JwtRedisRepositoryImpl implements JwtRedisRepository{

    private static final String KEY = "JWT";

    private final HashOperations<String, String, String> hashOperations;

    @Autowired
    public JwtRedisRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    public void addRefreshToken(String username, String refreshToken){
        hashOperations.put(KEY, username, refreshToken);
    }

    public String  findRefreshTokenByUsername(String username){
        return hashOperations.get(KEY, username);
    }

    public void deleteRefreshToken(String username) {
        hashOperations.delete(KEY, username);
    }

}
