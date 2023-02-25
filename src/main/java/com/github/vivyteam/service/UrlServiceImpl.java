package com.github.vivyteam.service;

import com.github.vivyteam.domain.Url;
import com.github.vivyteam.repository.RedisUrlRepository;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements URLService{

    private RedisUrlRepository redisUrlRepository;

    public UrlServiceImpl(RedisUrlRepository redisUrlRepository) {
        this.redisUrlRepository = redisUrlRepository;
    }

    @Override
    public Mono<Url> save(String longUrl) {
        String id =  Hashing.murmur3_32().hashString(longUrl, Charset.defaultCharset()).toString();
        Url url = new Url(id, longUrl, LocalDateTime.now());
        return redisUrlRepository.save(url);
    }

    public Mono<Url> findByShortenUrl(String shortenUrl) {
        String key = getUniqueId(shortenUrl);
        return redisUrlRepository.findById(key);
    }

    private String getUniqueId(String shortenUrl) {
        String [] splitShortenUrl = shortenUrl.split("/");
        return splitShortenUrl[splitShortenUrl.length - 1];
    }

}
