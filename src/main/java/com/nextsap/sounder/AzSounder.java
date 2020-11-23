package com.nextsap.sounder;

import com.nextsap.sounder.config.ConfigManager;
import com.nextsap.sounder.graphics.frames.DefaultFrame;
import com.nextsap.sounder.setup.Initializer;

public class AzSounder {

    private static final DefaultFrame defaultFrame = new DefaultFrame();
    private static ConfigManager configManager;

    public static void main(String[] args) {
        boolean setup = new Initializer().initialize();

        if (!setup) System.exit(0);

        configManager = new ConfigManager();

        defaultFrame.show();
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
}
