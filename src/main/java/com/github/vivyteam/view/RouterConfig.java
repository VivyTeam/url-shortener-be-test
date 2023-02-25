package com.github.vivyteam.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> routes(ShortenerUrlHandler handler) {
        return route(POST("/shortener").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(GET("/shortener/{shortenUrl}"), handler::getLongUrl)
                .andRoute(GET("/shortener/{shortenUrl}/redirect"), handler::redirect);
    }
}
