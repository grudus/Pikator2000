package com.grudus.pikator2000.browser;

import com.grudus.pikator2000.Pair;

import java.util.List;
import java.util.Map;

import static com.grudus.pikator2000.Pair.of;
import static java.util.stream.Collectors.toMap;

public class OfferExtractorFactory {


    public static Map<String, OfferExtractor> create(List<String> urls, DriverCreator creator) {
        return urls.stream()
                .map(url -> of(url, WebsiteType.convert(url)))
                .map(urlAndType -> of(urlAndType.getFirst(), extract(urlAndType.getSecond().get(), creator)))
                .collect(toMap(Pair::getFirst, Pair::getSecond));

    }

    private static OfferExtractor extract(WebsiteType type, DriverCreator creator) {
        switch (type) {
            case OLX:
                return new OlxOfferExtractor(creator.create());
            case GUMTREE:
                return new GumtreeOfferExtractor(creator.create());
        }
        throw new IllegalArgumentException("Cannot find extractor for type " + type);
    }
}
