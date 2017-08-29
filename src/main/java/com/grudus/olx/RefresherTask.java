package com.grudus.olx;

import com.grudus.olx.browser.WebDriverRefresher;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.time.LocalTime;

import static com.grudus.olx.browser.WebsiteType.OLX;
import static java.time.LocalDateTime.now;

public class RefresherTask extends Task<Void> {
    public static final int DEFAULT_REFRESH = 30;

    private final WebDriverRefresher webDriverRefresher;
    private final TextArea output;
    private final int secondsToRefresh;

    private volatile boolean running;

    RefresherTask(TextArea output, int secondsToRefresh, String url) {
        this.output = output;
        this.secondsToRefresh = secondsToRefresh;
        this.webDriverRefresher = new WebDriverRefresher(getUrl(url), OLX);
        running = true;
    }

    @Override
    protected Void call() throws Exception {
        webDriverRefresher.initBrowser();
        try {
            while (running) {
                System.out.println("Running " + now());
                output.setText(getFormattedOffer() + output.getText());
                Thread.sleep(secondsToRefresh * 1000);
                webDriverRefresher.refresh();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    void stop() {
        running = false;
        webDriverRefresher.stop();
    }

    private String getFormattedOffer() {
        return String.format("%s - %s\n", LocalTime.now(), webDriverRefresher.getOffer());
    }

    private String getUrl(String url) {
        return isEmpty(url) ? WebDriverRefresher.DEFAULT_URL : url;
    }

    private boolean isEmpty(String url) {
        return url == null || url.replaceAll("\\s+", "").isEmpty();
    }
}
