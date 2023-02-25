package com.github.vivyteam.domain;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.redis.core.RedisHash;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Objects;

@RedisHash
public class Url {
    @Nonnull
    private String id;
    private String url;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    public Url(){}
    public Url(@Nonnull String id, String url, LocalDateTime createdAt) {
        this.id = id;
        this.url = url;
        this.createdAt = createdAt;
    }

    @Nonnull
    public String getId() {
        return id;
    }

    public void setId(@Nonnull String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return id.equals(url.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
