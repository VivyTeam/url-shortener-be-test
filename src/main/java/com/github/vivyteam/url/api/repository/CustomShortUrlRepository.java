package com.github.vivyteam.url.api.repository;


public interface CustomShortUrlRepository {
    Long findShortByUrl(String url);
}
