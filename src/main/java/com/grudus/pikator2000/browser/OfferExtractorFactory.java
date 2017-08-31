package com.grudus.pikator2000.browser;

import org.openqa.selenium.WebDriver;

public class OfferExtractorFactory {


    public static OfferExtractor create(WebsiteType type, WebDriver driver) {
        switch (type) {
            case OLX: return new OlxOfferExtractor(driver);
            case GUMTREE: return new GumtreeOfferExtractor(driver);
        }
        throw new IllegalArgumentException("Cannot find extractor for type " + type);
    }
}
