package com.github.vivyteam.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperUtils {

    private static final ObjectMapper mapper = createObjectMapper();

    public static final String URL_KEY = "URL";

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static <T> T objectMapper(Object obj, Class<T> contentClass){
      return mapper.convertValue(obj, contentClass);
    }


}
