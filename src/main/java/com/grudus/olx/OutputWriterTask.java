package com.grudus.olx;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;

public class OutputWriterTask extends Task<Void> {
    public static final int DEFAULT_REFRESH = 30;

    private final TextArea output;
    private final int secondsToRefresh;
    private volatile String text;
    private volatile boolean running;

    public OutputWriterTask(TextArea output, int secondsToRefresh, String text) {
        this.output = output;
        this.secondsToRefresh = secondsToRefresh;
        running = true;
        this.text = text;
    }

    @Override
    protected Void call() throws Exception {
        try {
            while (running) {
                output.setText(text + "\n" + output.getText());
                System.out.println("Running " + LocalDateTime.now());
                Thread.sleep(secondsToRefresh * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stop() {
        running = false;
    }

    public void setText(String text) {
        this.text = text;
    }
}
