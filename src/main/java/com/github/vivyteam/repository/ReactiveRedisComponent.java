package com.github.vivyteam.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ReactiveRedisComponent {

    private final ReactiveRedisOperations<String, Object> redisOperations;

    public ReactiveRedisComponent(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    public Mono<Object> set(String key, String hashKey, Object val) {
        return redisOperations.opsForHash().put(key, hashKey, val).map(b -> val);
    }

    public Flux<Object> get(String key){
       return redisOperations.opsForHash().values(key);
    }

    public Mono<Object> get(String key, Object hashKey) {
        return redisOperations.opsForHash().get(key, hashKey);
    }
}
