package com.nextsap.sounder.utils;

import java.util.regex.Pattern;

public class SplitUtils {

    private static final String playerPattern = Pattern.compile("([a-zA-Z0-9_]{3,16})").pattern();

    public static boolean isChat(String line) {
        if (!line.contains(": @") && !line.contains(": ")) return false;
        String pseudo = line.split(":")[0].split(" ")[line.split(":")[0].split(" ").length - 1];
        return pseudo.matches(playerPattern);
    }

    public static boolean isPrivateMessage(String line) {
        if (!line.contains(" -> Moi][R] ")) return false;
        String pseudo = line.split(" ")[1];
        return pseudo.matches(playerPattern);
    }

    public static boolean isFreecube(String line) {
        if (!line.contains(" -> ") && !line.contains("]")) return false;

        String pseudo = line.split(" ")[1];
        String zone = line.split(" -> ")[1].split("] ")[0];

        return pseudo.matches(playerPattern) && (zone.matches("([A-D][0-9]{1,6})") || line.contains(" -> Spawn] "));
    }

    public static boolean isParty(String line) {
        if (!line.contains(" [Groupe] ") && !line.contains(": &")) return false;
        String pseudo = line.split(":")[0].split(" ")[line.split(":")[0].split(" ").length - 1];
        return pseudo.matches(playerPattern);
    }

    public static boolean isStaffChat(String line) {
        if (!line.startsWith("[Staff]") || !line.startsWith("[Modo]") || !line.startsWith("[SuperModo]") || !line.startsWith("[Admin]"))
            return false;
        String pseudo = line.split(":")[0].split(" ")[line.split(":")[0].split(" ").length - 1];
        return pseudo.matches(playerPattern);
    }

    public static String parseGlobal(String line) {
        return line.toLowerCase();
    }

    public static String parseChat(String line) {
        if (isChat(line)) {
            if (line.contains(": @"))
                return " " + ArraysUtils.split(line, ": @")[1].toLowerCase();
            return " " + ArraysUtils.split(line, ": ")[1].toLowerCase();
        }
        if (isFreecube(line))
            return " " + ArraysUtils.split(line, "] ")[1].toLowerCase();
        return null;
    }

    public static String parsePrivateMessage(String line) {
        return " " + ArraysUtils.split(line, "]")[1].replace("[R]", "").toLowerCase();
    }

    public static String parseParty(String line) {
        return " " + ArraysUtils.split(line, ": &")[1].toLowerCase();
    }


    public static String parseStaff(String line) {
        return " " + ArraysUtils.split(line, ": ")[1].toLowerCase();
    }
}
