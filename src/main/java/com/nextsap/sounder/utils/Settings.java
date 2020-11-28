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

    /**
     * @return Tray Icon path
     */
    public static String getTrayIconPath() {
        String iconPath = "C:\\Users\\${name}\\AppData\\Roaming\\AzSounder\\trayicon.png";
        return iconPath.replace("${name}", System.getProperty("user.name"));
    }

    public static String getCurrentLogPath() {
        String path = "C:\\Users\\${name}\\AppData\\Roaming\\AzSounder\\CurrentLogs.txt";
        return path.replace("${name}", System.getProperty("user.name"));
    }
}
