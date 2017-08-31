package com.grudus.pikator2000;

import com.grudus.pikator2000.browser.WebDriverRefresher;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

public class RefresherTask extends Task<Void> {
    public static final int DEFAULT_REFRESH = 30;

    private final WebDriverRefresher webDriverRefresher;
    private final TextArea output;
    private final int secondsToRefresh;

    private volatile boolean running;

    RefresherTask(TextArea output, int secondsToRefresh, List<String> urls) {
        this.output = output;
        this.secondsToRefresh = secondsToRefresh;
        this.webDriverRefresher = new WebDriverRefresher(getUrls(urls));
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
        return OutputFormatter.format(webDriverRefresher.getOffers());
    }

    private List<String> getUrls(List<String> urls) {
        return urls.stream()
                .map(this::getUrl)
                .distinct()
                .collect(toList());
    }
    private String getUrl(String url) {
        return isEmpty(url) ? WebDriverRefresher.DEFAULT_URL : url;
    }

    private boolean isEmpty(String url) {
        return url == null || url.replaceAll("\\s+", "").isEmpty();
    }
}
