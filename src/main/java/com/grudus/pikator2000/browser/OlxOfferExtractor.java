package com.grudus.pikator2000.browser;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class OlxOfferExtractor implements OfferExtractor {
    private String title;
    private String place;
    private String price;

    private final WebDriver driver;

    public OlxOfferExtractor(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public OfferExtractor update() {
        this.title = driver.findElement(xpath("//*[@id=\"offers_table\"]/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/div/h3/a/strong")).getText();
        this.place = driver.findElement(xpath("//*[@id=\"offers_table\"]/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/div/p[1]/small/span")).getText();
        this.price = driver.findElement(xpath("//*[@id=\"offers_table\"]/tbody/tr[2]/td/table/tbody/tr[1]/td[3]/div/p/strong")).getText();
        return this;
    }

    @Override
    public Offer extract() {
        return new Offer(title, place, price);
    }
}
