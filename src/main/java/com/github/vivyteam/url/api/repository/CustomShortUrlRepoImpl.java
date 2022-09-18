package com.github.vivyteam.url.api.repository;

import com.github.vivyteam.url.api.contract.ShortenedUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomShortUrlRepoImpl implements CustomShortUrlRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomShortUrlRepoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Long findShortByUrl(String url) {
        Query query = new Query(Criteria.where("url").is(url));
        ShortenedUrl shortUrl = mongoTemplate.findOne(query,ShortenedUrl.class);
        return shortUrl.getId();
    }
}
