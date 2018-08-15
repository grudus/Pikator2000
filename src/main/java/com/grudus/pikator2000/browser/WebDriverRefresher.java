package com.grudus.pikator2000.browser;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebDriverRefresher {
    public static final String DEFAULT_URL = "https://www.olx.pl/nieruchomosci/mieszkania/wynajem/q-wroc%C5%82aw/?search%5Bfilter_float_price%3Ato%5D=2000&search%5Bfilter_enum_rooms%5D%5B0%5D=two&search%5Bphotos%5D=1&search%5Bprivate_business%5D=private";

    private final Map<String, OfferExtractor> urlToExtractor;
    private final Map<String, Offer> urlToCurrentOffer;

    public WebDriverRefresher(List<String> urls) {
        this.urlToExtractor = OfferExtractorFactory.create(urls, ChromeDriver::new);
        urlToCurrentOffer = new HashMap<>();
    }

    public WebDriverRefresher initBrowser() {
        urlToExtractor.forEach((url, extractor) -> {
            extractor.getDriver().get(url);
            urlToCurrentOffer.put(url, extractor.update().extract());
        });
        return this;
    }

    public WebDriverRefresher refresh() {
        urlToExtractor.forEach((url, extractor) -> {
            extractor.getDriver().navigate().refresh();
            Offer newDto = extractor.update().extract();
            Offer oldDto = urlToCurrentOffer.get(url);

            if (!newDto.equals(oldDto)) {
                urlToCurrentOffer.put(url, newDto);
                new Notificator().notifyNewOffer();
            }

        });

        return this;
    }

    public void stop() {
        urlToExtractor.values()
                .forEach(extractor -> extractor.getDriver().quit());
    }

    public List<Offer> getOffers() {
        return new ArrayList<>(urlToCurrentOffer.values());
    }
}
