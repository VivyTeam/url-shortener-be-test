package com.github.vivyteam.builder;

import com.github.vivyteam.domain.Url;

import java.time.LocalDateTime;

public class UrlBuilder {

    public static Url create() {
        return new Url("1aeb4cfa",
                "https://www.nytimes.com/live/2023/02/24/world/russia-ukraine-zelensky-news",
                LocalDateTime.now());
    }

}
