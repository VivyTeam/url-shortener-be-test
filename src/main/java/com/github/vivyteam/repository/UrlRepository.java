package com.github.vivyteam.repository;

import com.github.vivyteam.domain.Url;
import reactor.core.publisher.Mono;
public interface UrlRepository {

    Mono<Url> save(Url url);

    Mono<Url> findById(String id);

}
