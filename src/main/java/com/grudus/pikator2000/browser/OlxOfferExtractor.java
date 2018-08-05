package com.grudus.pikator2000.browser;

import org.openqa.selenium.WebDriver;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class OlxOfferExtractor implements OfferExtractor {
    private static final String OFFER_PATTERN = "//*[@id=\"offers_table\"]/tbody/tr[%d]/td/div/table/tbody%s";


    private String title;
    private String place;
    private String price;
    private int position;

    private final WebDriver driver;

    public OlxOfferExtractor(WebDriver driver) {
        this.driver = driver;
        this.position = 2;
    }

    @Override
    public OfferExtractor update() {
        this.position = findPositionOfFirstNotRecommendedOffer();
        System.out.printf("Skipped %d recommended positions\n", position - 2);
        this.title = driver.findElement(xpath(format(OFFER_PATTERN, position, "/tr[1]/td[2]/div/h3/a/strong"))).getText();
        this.place = driver.findElement(xpath(format(OFFER_PATTERN, position, "/tr[2]/td[1]/div/p/small[1]/span"))).getText();
        this.price = driver.findElement(xpath(format(OFFER_PATTERN, position, "/tr[1]/td[3]/div/p/strong"))).getText();
        return this;
    }

    private int findPositionOfFirstNotRecommendedOffer() {
        int position = 2;
        for (int i = position; i < 20; i++) {
            boolean isRecommended = driver.findElements(xpath(format(OFFER_PATTERN, i, "/tr[1]/td[1]/a/span"))).size() > 0;
            if (!isRecommended)
                return i;
        }
        return 2;

    }

    @Override
    public Offer extract() {
        return new Offer(title, place, price);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }
}
