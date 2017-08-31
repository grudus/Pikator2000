package com.grudus.pikator2000;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class UrlTextFields extends VBox {

    private final String defaultText;
    private List<TextField> urlFields;


    public UrlTextFields(String defaultText) {
        this.defaultText = defaultText;
        urlFields = new ArrayList<>(1);
        initDefaultView();

    }

    private void initDefaultView() {
        Insets padding = new Insets(10, 0, 10, 0);
        setSpacing(5);

        TextField defaultUrlField = new TextField();
        urlFields.add(defaultUrlField);

        defaultUrlField.setPromptText("Olx url");
        defaultUrlField.setText(defaultText);
        defaultUrlField.setPadding(padding);

        Button addNewButton = new Button("ADD NEW");
        addNewButton.setOnAction(e -> addNewUrlField());

        getChildren().addAll(defaultUrlField, addNewButton);
    }

    private void addNewUrlField() {
        TextField newField = new TextField();
        newField.setPadding(new Insets(10, 0, 10, 0));
        urlFields.add(newField);
        getChildren().add(0, newField);
        newField.requestFocus();
    }

    public List<String> getUrls() {
        return urlFields.stream().map(TextField::getText)
                .filter(Objects::nonNull)
                .filter(url -> !url.replaceAll("\\s+", "").isEmpty())
                .collect(toList());
    }
}