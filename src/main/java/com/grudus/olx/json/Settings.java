package com.grudus.olx.json;

public class Settings {
    private String url;
    private Integer seconds;

    public Settings(String defaultUrl, int defaultRefresh) {
        this.url = defaultUrl;
        this.seconds = defaultRefresh;
    }

    public Settings() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
}
