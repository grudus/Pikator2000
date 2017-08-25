package com.grudus.olx;

import com.grudus.olx.json.JsonIO;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

import static java.lang.System.setProperty;

public class Window extends Application {
    public static final String CONFIG_FILE = "config.json";
    public static final String DRIVER_FILE = "chromedriver";

    private MainScene scene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new MainScene(new StackPane(), 800, 600, new JsonIO(CONFIG_FILE));

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
        try {
            assertFiles();
            setProperty("webdriver.chrome.driver", new File(DRIVER_FILE).getAbsolutePath());
            launch(args);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    private static void assertFiles() throws Exception {
        assertFile(CONFIG_FILE);
        assertFile(DRIVER_FILE);
    }

    private static void assertFile(String path) throws Exception {
        if (!new File(path).exists()) {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JOptionPane.showMessageDialog(null, "Cannot find file " + path, "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Cannot find file " + path);
        }
    }
}
