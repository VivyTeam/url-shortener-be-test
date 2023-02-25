package com.github.vivyteam.service;

import com.github.vivyteam.domain.Url;
import reactor.core.publisher.Mono;

public interface URLService {

   Mono<Url> save(String longUrl);
   Mono<Url> findByShortenUrl(String shortenUrl);


}
