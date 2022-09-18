package com.github.vivyteam.url.api.repository;


import com.github.vivyteam.url.api.contract.FullUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomFullUrlRepository {
    String findFullById(Long id);
}
