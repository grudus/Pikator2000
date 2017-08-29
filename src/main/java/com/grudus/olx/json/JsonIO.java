package com.grudus.olx.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grudus.olx.browser.WebDriverRefresher;

import java.io.File;
import java.io.IOException;

import static com.grudus.olx.RefresherTask.DEFAULT_REFRESH;

public class JsonIO {
    private final File file;
    private final ObjectMapper mapper;

    public JsonIO(String file) {
        this.file = new File(file);
        this.mapper = new ObjectMapper();
    }

    public Settings read() {
        try {
            return mapper.readValue(file, Settings.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Settings(WebDriverRefresher.DEFAULT_URL, DEFAULT_REFRESH);
    }

    public void save(Settings settings) {
        try {
            mapper.writeValue(file, settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


