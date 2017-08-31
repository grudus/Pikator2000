package com.grudus.pikator2000.browser;

import java.util.Optional;
import java.util.stream.Stream;

public enum WebsiteType {
    OLX("olx.pl"),
    GUMTREE("gumtree.pl");

    public final String url;

    WebsiteType(String url) {
        this.url = url;
    }

    public static Optional<WebsiteType> convert(String url) {
        return Stream.of(values())
                .filter(type -> url.contains(type.url))
                .findFirst();
    }
}
