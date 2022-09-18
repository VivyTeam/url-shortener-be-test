package com.github.vivyteam.url.api.repository;

import com.github.vivyteam.url.api.contract.FullUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CustomFullUrlRepoImpl implements CustomFullUrlRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomFullUrlRepoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public String findFullById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        FullUrl fullUrl = mongoTemplate.findOne(query,FullUrl.class);
        return fullUrl.getUrl();
    }
}
