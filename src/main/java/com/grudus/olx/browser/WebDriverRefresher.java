package com.grudus.olx.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverRefresher {
    public static final String DEFAULT_URL = "https://www.olx.pl/nieruchomosci/mieszkania/wynajem/q-wroc%C5%82aw/?search%5Bfilter_float_price%3Ato%5D=2000&search%5Bfilter_enum_rooms%5D%5B0%5D=two&search%5Bphotos%5D=1&search%5Bprivate_business%5D=private";

    private final String url;
    private final WebDriver webDriver;

    private final OfferExtractor offerExtractor;
    private Offer current;

    public WebDriverRefresher(String url, WebsiteType type) {
        this.url = url;
        this.webDriver = new ChromeDriver();
        this.offerExtractor = OfferExtractorFactory.create(type, webDriver);
    }

    public WebDriverRefresher initBrowser() {
        webDriver.get(url);
        this.current = offerExtractor.extract();
        return this;
    }

    public WebDriverRefresher refresh() {
        webDriver.navigate().refresh();

        Offer newDto =  offerExtractor.update()
                .extract();

        if (!newDto.equals(current)) {
            current = newDto;
            new Notificator().notifyNewOffer();
        }

        return this;
    }

    public void stop() {
        webDriver.quit();
    }

    public Offer getOffer() {
        return offerExtractor.extract();
    }
}
