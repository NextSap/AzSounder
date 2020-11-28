package com.nextsap.sounder.utils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SoundUtils {

    public static void play(String fileName) {
        try {
            InputStream in = new FileInputStream(new File(fileName));
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
