package com.grudus.pikator2000.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grudus.pikator2000.browser.WebDriverRefresher;

import java.io.File;
import java.io.IOException;

import static com.grudus.pikator2000.RefresherTask.DEFAULT_REFRESH;
import static java.util.Collections.singletonList;

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
        return new Settings(singletonList(WebDriverRefresher.DEFAULT_URL), DEFAULT_REFRESH);
    }

    public void save(Settings settings) {
        try {
            mapper.writeValue(file, settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


