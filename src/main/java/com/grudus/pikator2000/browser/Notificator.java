package com.grudus.pikator2000.browser;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;

class Notificator {
    private AudioStream stream;

    Notificator() {
        try {
            this.stream = new AudioStream(Notificator.class.getClassLoader().getResourceAsStream("alarm.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyNewOffer() {
        System.err.println("NEW ORDER NEW ORDER");
        AudioPlayer.player.start(stream);
    }
}

