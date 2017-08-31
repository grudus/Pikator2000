package com.grudus.pikator2000.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GumtreeOfferExtractor implements OfferExtractor {
    private String title;
    private String place;
    private String price;

    private final WebDriver driver;

    public GumtreeOfferExtractor(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public OfferExtractor update() {
        String[] parts = driver.findElement(By.xpath("//div[@class=\"view\"]")).getText().split("\\n+");
        int offset = parts[0].startsWith("Zdj") ? 1 : 0;
        this.title = parts[offset];
        this.place = parts[1 + offset];
        this.price = parts[2 + offset];
        return this;
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
