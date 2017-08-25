package com.grudus.olx;

import com.grudus.olx.browser.Refresher;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.LocalDate.now;

public class OutputWriterTask extends Task<Void> {
    public static final int DEFAULT_REFRESH = 30;

    private final Refresher refresher;
    private final TextArea output;
    private final int secondsToRefresh;
    private volatile boolean running;


    public OutputWriterTask(TextArea output, int secondsToRefresh, String url) {
        this.output = output;
        this.secondsToRefresh = secondsToRefresh;
        this.refresher = new Refresher(getUrl(url));
        running = true;
    }

    @Override
    protected Void call() throws Exception {
        refresher.initBrowser();
        try {
            while (running) {
                System.out.println("Running " + LocalDateTime.now());
                output.setText(getNewCurrentOffer() + output.getText());
                Thread.sleep(secondsToRefresh * 1000);
                refresher.refresh();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getNewCurrentOffer() {
        return String.format("%s - %s\n", LocalTime.now(), refresher.getOffer().toDto());
    }

    public void stop() {
        running = false;
        refresher.stop();
    }

    private String getUrl(String url) {
        return isEmpty(url) ? Refresher.DEFAULT_URL : url;
    }

    private boolean isEmpty(String url) {
        return url == null || url.replaceAll("\\s+", "").isEmpty();
    }
}
