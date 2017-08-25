package com.grudus.olx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Window extends Application {
    private MainScene scene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new MainScene(new StackPane(), 500, 500);

        primaryStage.setTitle("Olx search");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        scene.close();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
