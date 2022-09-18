package com.github.vivyteam.url.api;

import com.github.vivyteam.url.api.contract.FullUrl;
import com.github.vivyteam.url.api.contract.ShortenedUrl;
import com.github.vivyteam.url.api.service.FullUrlService;
import com.github.vivyteam.url.api.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class UrlApi {

    @Autowired
    private FullUrlService fullService;

    @Autowired
    private ShortUrlService shortService;

    // call example "http://localhost:9000/www.google.com/short"
    @GetMapping("/{url}/short")
    public Mono<String> shortUrl(@PathVariable final String url) {
        // generate id to be used both in fullurl and shorturl documents
        long generatedId = ThreadLocalRandom.current().nextLong(1000);

        String str = "http://localhost:9000/" + randomUrlGenerator();
        ShortenedUrl shortUrl = new ShortenedUrl(generatedId, str);
        Mono<ShortenedUrl> shortMono = Mono.just(shortUrl);

        FullUrl fullUrl = new FullUrl(generatedId, url);
        Mono<FullUrl> fullMono = Mono.just(fullUrl);

        // save full and short urls to their respected documents
        shortMono.flatMap(i -> shortService.saveShortUrl(shortMono)).
                flatMap(i -> fullService.saveFullUrl(fullMono)).subscribe();

        return Mono.just(shortUrl.getUrl());
    }

    // call example "http://localhost:9000/andjfk123/full"
    @GetMapping("/{shortenedUrl}/full")
    public Mono<String> getFullUrl(@PathVariable final String shortenedUrl) {
        String str = "http://localhost:9000/" + shortenedUrl;
        Long shortUrlId = shortService.findShortByUrl(str);
        String fullUrl = fullService.findFullById(shortUrlId);


        return Mono.just(fullUrl);
    }

    // call example "http://localhost:9000/andjfk123"
    @GetMapping("/{shortenedUrl}")
    public Mono<Void> redirectToFullUrl(@PathVariable final String shortenedUrl, ServerHttpResponse response, ServerWebExchange exchange) throws URISyntaxException {
        String str = "http://localhost:9000/" + shortenedUrl;
        Long shortUrlId = shortService.findShortByUrl(str);
        String fullUrl = fullService.findFullById(shortUrlId);

        URI originalUri = new URI("//" + fullUrl);

        URI mutatedUri = new URI("https",
                originalUri.getUserInfo(),
                originalUri.getHost(),
                originalUri.getPort(),
                originalUri.getPath(),
                originalUri.getQuery(),
                originalUri.getFragment());

        response = exchange.getResponse();
        response.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        response.getHeaders().setLocation(mutatedUri);
        return response.setComplete();
    }

    private String randomUrlGenerator() {
        int n = 10;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }


    @GetMapping("/fullUrlList")
    public Flux<FullUrl> getFullUrlList() {
        return fullService.getFullUrlList();
    }

    @PostMapping("/createFullUrl")
    public Mono<FullUrl> createFullUrl(@RequestBody Mono<FullUrl> fullUrl) {
        return fullService.saveFullUrl(fullUrl);
    }

    @GetMapping("/shortUrlList")
    public Flux<ShortenedUrl> getShortenedUrlList() {
        return shortService.getShortenedUrlList();
    }

    @PostMapping("/createShortUrl")
    public Mono<ShortenedUrl> createShortenedUrl(@RequestBody Mono<ShortenedUrl> shortUrl) {
        return shortService.saveShortUrl(shortUrl);
    }

}
