package dev.vorstu.coworkingapp.entities.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("refreshTokens")
@Data
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String id;

    private String token;

}
