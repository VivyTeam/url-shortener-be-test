package com.github.vivyteam.url.api.service;

import com.github.vivyteam.url.api.contract.FullUrl;
import com.github.vivyteam.url.api.repository.CustomFullUrlRepoImpl;
import com.github.vivyteam.url.api.repository.FullUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FullUrlService {

    @Autowired
    private FullUrlRepository repo;

    @Autowired
    private CustomFullUrlRepoImpl customRepo;

    public Flux<FullUrl> getFullUrlList() {
        return repo.findAll();
    }

    public Mono<FullUrl> getFullUrl(Long id) {
        return repo.findById(id);
    }

    public Mono<FullUrl> saveFullUrl(Mono<FullUrl> url) {
        return url.flatMap(repo::insert);
    }

    public String findFullById(Long url) {
        return customRepo.findFullById(url);
    }
}
