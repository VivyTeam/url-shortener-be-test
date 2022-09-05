package com.github.vivyteam.url.api;

import com.github.vivyteam.url.api.contract.FullUrl;
import com.github.vivyteam.url.api.contract.ShortenedUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UrlApi {

    @GetMapping("/{url}/short")
    public Mono<ShortenedUrl> shortUrl(@PathVariable final String url) {
        // TODO: implement logic to shorten the url
        return Mono.just(new ShortenedUrl(url));
    }

    @GetMapping("/{shortenedUrl}/full")
    public Mono<FullUrl> getFullUrl(@PathVariable final String shortenedUrl) {
        // TODO: implement logic to fetch the full url
        return Mono.just(new FullUrl(shortenedUrl));
    }

}
