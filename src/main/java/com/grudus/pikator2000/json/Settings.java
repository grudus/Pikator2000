package com.grudus.pikator2000.json;

import java.util.List;

public class Settings {
    private List<String> urls;
    private Integer seconds;

    public Settings(List<String> defaultUrl, int defaultRefresh) {
        this.urls = defaultUrl;
        this.seconds = defaultRefresh;
    }

    public Settings() {
    }

    public List<String> getUrl() {
        return urls;
    }

    public void setUrl(List<String> url) {
        this.urls = url;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
}
