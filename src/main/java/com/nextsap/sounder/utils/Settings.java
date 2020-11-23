package com.nextsap.sounder.utils;

public class Settings {

    /**
     * @return Icon path
     */
    public static String getIconPath() {
        String iconPath = "C:\\Users\\${name}\\AppData\\Roaming\\AzSounder\\icon.png";
        return iconPath.replace("${name}", System.getProperty("user.name"));
    }

    /**
     * @return Log path
     */
    public static String getLogPath() {
        String path = "C:\\Users\\${name}\\AppData\\Roaming\\.az-client\\logs\\latest.log";
        return path.replace("${name}", System.getProperty("user.name"));
    }
}
