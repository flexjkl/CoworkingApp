package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.redis.RefreshToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RedisRepository extends KeyValueRepository<RefreshToken, String> {}
