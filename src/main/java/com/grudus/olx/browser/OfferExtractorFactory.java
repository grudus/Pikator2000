package com.grudus.olx.browser;

import org.openqa.selenium.WebDriver;

public class OfferExtractorFactory {


    public static OfferExtractor create(WebsiteType type, WebDriver driver) {
        switch (type) {
            case OLX: return new OlxOfferExtractor(driver);
        }
        throw new IllegalArgumentException("Cannot find extractor for type " + type);
    }
}
