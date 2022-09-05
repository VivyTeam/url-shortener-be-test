package com.github.vivyteam.url.api.contract;

public class ShortenedUrl {
    private String url;

    public ShortenedUrl() {
    }

    public ShortenedUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
