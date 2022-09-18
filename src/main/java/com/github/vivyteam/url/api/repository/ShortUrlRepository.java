package com.github.vivyteam.url.api.repository;

import com.github.vivyteam.url.api.contract.ShortenedUrl;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends ReactiveMongoRepository<ShortenedUrl, Long>, CustomShortUrlRepository {


}
