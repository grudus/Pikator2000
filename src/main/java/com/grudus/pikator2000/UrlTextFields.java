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

    private final List<String> defaultUrls;
    private final Insets padding = new Insets(10, 0, 10, 0);
    private List<TextField> urlFields;


    public UrlTextFields(List<String> defaultUrls) {
        this.defaultUrls = defaultUrls;
        urlFields = new ArrayList<>(defaultUrls.size());
        initDefaultView();

    }

    public List<String> getUrls() {
        return urlFields.stream().map(TextField::getText)
                .filter(Objects::nonNull)
                .filter(url -> !url.replaceAll("\\s+", "").isEmpty())
                .collect(toList());
    }

    private void initDefaultView() {
        setSpacing(5);

        List<TextField> fields = defaultUrls.stream().map(this::mapToField)
                .collect(toList());


        Button addNewButton = new Button("ADD NEW");
        addNewButton.setOnAction(e -> addNewUrlField());

        getChildren().addAll(fields);
        getChildren().add(addNewButton);
    }

    private TextField mapToField(String url) {
        TextField defaultUrlField = new TextField();
        urlFields.add(defaultUrlField);

        defaultUrlField.setPromptText("Url to search");
        defaultUrlField.setText(url);
        defaultUrlField.setPadding(padding);
        return defaultUrlField;
    }

    private void addNewUrlField() {
        TextField newField = new TextField();
        newField.setPadding(new Insets(10, 0, 10, 0));
        urlFields.add(newField);
        getChildren().add(0, newField);
        newField.requestFocus();
    }
}