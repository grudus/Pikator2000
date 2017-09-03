package com.grudus.pikator2000;

import com.grudus.pikator2000.json.JsonIO;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

import static java.lang.System.setProperty;

public class Window extends Application {
    private static final String CONFIG_FILE = "config.json";

    private MainScene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new MainScene(new StackPane(), 800, 600, new JsonIO(CONFIG_FILE));

        primaryStage.setTitle("Pikator 2000");
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
            String driverFileName = DriverDetector.INSTANCE.detectFileName();
            assert(driverFileName != null); //xD

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            assertFiles(driverFileName);
            setProperty("webdriver.chrome.driver", new File(driverFileName).getAbsolutePath());
            launch(args);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    private static void assertFiles(String driverFile) throws Exception {
        assertFile(CONFIG_FILE);
        assertFile(driverFile);
    }

    private static void assertFile(String path) throws Exception {
        if (!new File(path).exists()) {
            JOptionPane.showMessageDialog(null, "Cannot find file " + path, "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Cannot find file " + path);
        }
    }
}
