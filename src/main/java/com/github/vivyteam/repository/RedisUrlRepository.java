package com.github.vivyteam.repository;

import com.github.vivyteam.config.ObjectMapperUtils;
import com.github.vivyteam.domain.Url;
import com.github.vivyteam.service.URLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static com.github.vivyteam.config.ObjectMapperUtils.URL_KEY;

@Repository
public class RedisUrlRepository implements UrlRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLService.class);
    private final ReactiveRedisComponent reactiveRedisComponent;

    public RedisUrlRepository(ReactiveRedisComponent reactiveRedisComponent) {
        this.reactiveRedisComponent = reactiveRedisComponent;
    }

    @Override
    public Mono<Url> findById(String key) {
        LOGGER.info("Getting url with key: { " + key + " }");
        return reactiveRedisComponent.get(URL_KEY, key)
                .flatMap(u -> Mono.just(ObjectMapperUtils.objectMapper(u, Url.class)));
    }
    @Override
    public Mono<Url> save(Url url) {
        LOGGER.info("Saving url: { " + url.getId() + " }");
        return reactiveRedisComponent.set(URL_KEY, url.getId(), url).map(u -> url);
    }
}
