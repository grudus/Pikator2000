package com.grudus.olx.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Offer {
    private String title;
    private String place;
    private String price;

    private final WebDriver driver;

    public Offer(WebDriver driver) {
        this.driver = driver;
        update();
    }

    public Offer update() {
        this.title = driver.findElement(By.xpath("//*[@id=\"offers_table\"]/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/div/h3/a/strong")).getText();
        this.place = driver.findElement(By.xpath("//*[@id=\"offers_table\"]/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/div/p[1]/small/span")).getText();
        this.price = driver.findElement(By.xpath("//*[@id=\"offers_table\"]/tbody/tr[2]/td/table/tbody/tr[1]/td[3]/div/p/strong")).getText();
        return this;
    }

    public OfferDto toDto() {
        return new OfferDto(title, place, price);
    }
}
