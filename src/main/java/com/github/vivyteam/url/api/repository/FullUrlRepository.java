package com.github.vivyteam.url.api.repository;

import com.github.vivyteam.url.api.contract.FullUrl;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FullUrlRepository extends ReactiveMongoRepository<FullUrl, Long>, CustomFullUrlRepository {

}
