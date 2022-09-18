package com.github.vivyteam.url.api.repository;


import com.github.vivyteam.url.api.contract.ShortenedUrl;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface CustomShortUrlRepository {
    Long findShortByUrl(String url);
}
