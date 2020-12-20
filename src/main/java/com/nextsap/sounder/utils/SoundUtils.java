package com.nextsap.sounder.utils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/*
    A class to manage sound
 */
public class SoundUtils {

    /**
     * Play a sound
     *
     * @param fileName the {@link String} path of the sound
     */
    public static void play(String fileName) {
        try {
            InputStream in = new FileInputStream(fileName);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
