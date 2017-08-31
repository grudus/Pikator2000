package com.grudus.pikator2000;

import com.grudus.pikator2000.json.JsonIO;
import com.grudus.pikator2000.json.Settings;
import javafx.scene.control.TextArea;

import java.util.List;

import static java.lang.Integer.valueOf;

class MainSceneController {
    private final JsonIO jsonIO;
    private RefresherTask task;

    MainSceneController(JsonIO jsonIO) {
        this.jsonIO = jsonIO;
    }

    void close() {
        if (task != null) {
            task.stop();
            task.cancel();
        }
    }

    Settings readSettings() {
        return jsonIO.read();
    }

    void onSubmit(TextArea logger, List<String> urls, String seconds) {
        logger.setText("Init process...");
        task = new RefresherTask(logger, getSecondsToRefresh(seconds), urls);
        new Thread(task).start();
        logger.setText("Searching for offers...\n" + logger.getText());
        updateSettings(urls, seconds);
    }


    void onCancel(TextArea logger) {
        logger.setText("Stopping process...\n" + logger.getText());
        task.stop();
        logger.setText("Stopped\n" + logger.getText());
    }

    private void updateSettings(List<String> urls, String seconds) {
        Settings settings = new Settings();
        settings.setUrls(urls);
        settings.setSeconds(getSecondsToRefresh(seconds));
        jsonIO.save(settings);
    }

    private Integer getSecondsToRefresh(String seconds) {
        return invalidTime(seconds) ? RefresherTask.DEFAULT_REFRESH : valueOf(seconds);
    }

    private boolean invalidTime(String seconds) {
        return seconds == null || !seconds.matches("\\d+");
    }
}
