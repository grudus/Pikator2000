package com.grudus.olx.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Refresher {
    public static final String DEFAULT_URL = "https://www.olx.pl/nieruchomosci/mieszkania/wynajem/q-wroc%C5%82aw/?search%5Bfilter_float_price%3Ato%5D=2000&search%5Bfilter_enum_rooms%5D%5B0%5D=two&search%5Bphotos%5D=1&search%5Bprivate_business%5D=private";
    private final String url;
    private final WebDriver webDriver;
    private Offer offer;
    private OfferDto current;

    public Refresher(String url) {
        this.url = url;
        this.webDriver = new ChromeDriver();
    }

    public Refresher initBrowser() {
        webDriver.get(url);
        this.offer = new Offer(webDriver);
        this.current = offer.toDto();
        return this;
    }

    public Refresher refresh() {
        webDriver.navigate().refresh();
        offer.update();

        OfferDto newDto = offer.toDto();
        if (!newDto.equals(current)) {
            current = newDto;
            new Notificator().notifyNewOffer();
        }

        return this;
    }

    public void stop() {
        offer = null;
        webDriver.quit();
    }

    public Offer getOffer() {
        return offer;
    }
}
