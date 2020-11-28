package com.nextsap.sounder;

import com.nextsap.sounder.config.ConfigManager;
import com.nextsap.sounder.graphics.frames.DefaultFrame;
import com.nextsap.sounder.setup.Initializer;

import java.net.ServerSocket;

public class AzSounder {

    private static final DefaultFrame defaultFrame = new DefaultFrame();
    public static ServerSocket ss;
    private static ConfigManager configManager;

    public static void main(String[] args) {
        try {
            ss = new ServerSocket(1044);
        } catch (Exception e) {
            System.exit(-1);
            return;
        }
        boolean setup = new Initializer().initialize(defaultFrame);
        if (!setup) System.exit(0);

        configManager = new ConfigManager();

        defaultFrame.show();
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
}
