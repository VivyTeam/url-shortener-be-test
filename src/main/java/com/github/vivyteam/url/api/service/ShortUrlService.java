package com.github.vivyteam.url.api.service;

import com.github.vivyteam.url.api.contract.ShortenedUrl;
import com.github.vivyteam.url.api.repository.CustomShortUrlRepoImpl;
import com.github.vivyteam.url.api.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ShortUrlService {

    @Autowired
    private ShortUrlRepository repo;

    @Autowired
    private CustomShortUrlRepoImpl customRepo;

    public Flux<ShortenedUrl> getShortenedUrlList() {
        return repo.findAll();
    }

    public Mono<ShortenedUrl> getShortenedUrl(Long id) {
        return repo.findById(id);
    }

    public Mono<ShortenedUrl> saveShortUrl(Mono<ShortenedUrl> url) {
        return url.flatMap(repo::insert);
    }

    public Long findShortByUrl(String url) {
        return customRepo.findShortByUrl(url);
    }
}
