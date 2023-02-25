package com.github.vivyteam.view;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class UrlDecoder {

    public static String decode(String value) throws UnsupportedEncodingException {
        return java.net.URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
    }

}
