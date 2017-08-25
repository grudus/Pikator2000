package com.grudus.olx;

import com.grudus.olx.json.JsonIO;
import com.grudus.olx.json.Settings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import static java.lang.Integer.valueOf;

public class MainScene extends Scene {
    private final JsonIO jsonIO;
    private final VBox layout;

    private TextField urlField;
    private TextField secondsField;
    private Button okButton;
    private Button cancelButton;
    private TextArea output;
    private OutputWriterTask task;

    public MainScene(Pane pane, int width, int height, JsonIO jsonIO) {
        super(pane, width, height);
        this.jsonIO = jsonIO;
        layout = new VBox(10);
        pane.getChildren().add(layout);
        initViews();
        addListeners();
    }

    public void close() {
        if (task != null) {
            task.stop();
            task.cancel();
        }

    }

    private void initViews() {
        Insets padding = new Insets(10, 0, 10, 0);
        layout.setPadding(new Insets(20, 20, 20, 20));
        Settings settings = jsonIO.read();

        urlField = new TextField();
        urlField.setPromptText("Olx url");
        urlField.setText(settings.getUrl());
        urlField.setPadding(padding);

        secondsField = new TextField();
        secondsField.setPromptText("Seconds to refresh");
        secondsField.setText(String.valueOf(settings.getSeconds()));
        secondsField.setPadding(padding);

        okButton = new Button("START");
        cancelButton = new Button("CANCEL");

        output = new TextArea();
        output.setPromptText("Here will be output");
        output.setPrefRowCount(20);

        layout.getChildren().addAll(urlField, secondsField, okButton, cancelButton, output);
    }

    private void addListeners() {

        okButton.setOnAction(e -> {
            output.setText("Init process...");
            task = new OutputWriterTask(output, getSecondsToRefresh(), urlField.getText());
            new Thread(task).start();
            output.setText("Searching for offers...\n" + output.getText());
            updateSettings();
        });

        cancelButton.setOnAction(e -> {
            output.setText("Stopping process...\n" + output.getText());
            task.stop();
            output.setText("Stopped\n" + output.getText());
        });
    }

    private void updateSettings() {
        Settings settings = new Settings();
        settings.setUrl(urlField.getText());
        settings.setSeconds(getSecondsToRefresh());
        jsonIO.save(settings);
    }

    private Integer getSecondsToRefresh() {
        return invalidTime() ? OutputWriterTask.DEFAULT_REFRESH : valueOf(secondsField.getText());
    }

    private boolean invalidTime() {
        String text = secondsField.getText();
        return text == null || !text.matches("\\d+");
    }
}
