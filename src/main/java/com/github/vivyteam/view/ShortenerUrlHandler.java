package com.github.vivyteam.view;
import com.github.vivyteam.service.URLService;
import com.github.vivyteam.view.request.ShortenerURLRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;

@Component
public class ShortenerUrlHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortenerUrlHandler.class);
    private final URLService urlService;

    public ShortenerUrlHandler(URLService urlService) {
        this.urlService = urlService;
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<ShortenerURLRequest> mono = request.bodyToMono(ShortenerURLRequest.class);
        String baseUrl = getBaseURL(request.uri().toString());
        LOGGER.info("Received url to shorten");
        return mono.flatMap(s ->
                urlService.save(s.url())
                .flatMap(u -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(baseUrl + u.getId())));
    }

    public Mono<ServerResponse> getLongUrl(ServerRequest request) {
        try {
            String [] pathSplit = request.path().split("/");
            String decodedShortenUrl = UrlDecoder
                    .decode(pathSplit[pathSplit.length - 1]);
            LOGGER.info("Received shorten url to return the original one: " + decodedShortenUrl);
            return urlService.findByShortenUrl(decodedShortenUrl)
                    .flatMap( url ->  ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(url.getUrl())
                    ).switchIfEmpty(ServerResponse.notFound().build());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Mono<ServerResponse> redirect(ServerRequest request) {
        try {
            String [] pathSplit = request.path().split("/");
            String decodedShortenUrl = UrlDecoder
                    .decode(pathSplit[pathSplit.length - 2]);
            LOGGER.info("Received shortened url to redirect: " + decodedShortenUrl);
            return urlService.findByShortenUrl(decodedShortenUrl)
                    .flatMap(u ->
                            ServerResponse.temporaryRedirect(URI.create(u.getUrl()))
                                    .build());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getBaseURL(String localURL) {
        String[] addressComponents = localURL.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }

}