package com.grudus.pikator2000;

import com.grudus.pikator2000.json.JsonIO;
import com.grudus.pikator2000.json.Settings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class MainScene extends Scene {
    private final MainSceneController controller;

    private UrlTextFields urlTextFields;
    private TextField secondsField;
    private Button okButton;
    private Button cancelButton;
    private TextArea output;

    MainScene(Pane pane, int width, int height, JsonIO jsonIO) {
        super(pane, width, height);

        controller = new MainSceneController(jsonIO);

        initViews(pane);
        addListeners();
    }

    void close() {
        controller.close();
    }

    private void initViews(Pane pane) {
        VBox layout = new VBox(10);
        pane.getChildren().add(layout);

        Insets padding = new Insets(10, 0, 10, 0);
        layout.setPadding(new Insets(20, 20, 20, 20));
        Settings settings = controller.readSettings();

        urlTextFields = new UrlTextFields(settings.getUrls());

        secondsField = new TextField();
        secondsField.setPromptText("Seconds to refresh");
        secondsField.setText(String.valueOf(settings.getSeconds()));
        secondsField.setPadding(padding);

        okButton = new Button("START");
        cancelButton = new Button("CANCEL");

        output = new TextArea();
        output.setPromptText("Here will be output");
        output.setPrefRowCount(20);

        layout.getChildren().addAll(urlTextFields, secondsField, okButton, cancelButton, output);
    }

    private void addListeners() {
        okButton.setOnAction(e -> controller.onSubmit(output, urlTextFields.getUrls(), secondsField.getText()));
        cancelButton.setOnAction(e -> controller.onCancel(output));
    }
}
