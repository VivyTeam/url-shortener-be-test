package com.github.vivyteam.url.api.repository;

import com.github.vivyteam.url.api.contract.FullUrl;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FullUrlRepository extends ReactiveMongoRepository<FullUrl, Long>, CustomFullUrlRepository {

}
