package com.github.vivyteam.url.api.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@Document("fullurl")
public class FullUrl {
    @Id
    private long id;
    private String url;

    public FullUrl() {
    }

    public FullUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
