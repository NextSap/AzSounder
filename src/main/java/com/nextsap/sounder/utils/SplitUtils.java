package com.nextsap.sounder.utils;

public class SplitUtils {

    public static boolean isChat(String line) {
        return line.contains("✴") && (line.contains(": @") || line.contains(": "));
    }

    public static boolean isPrivateMessage(String line) {
        return line.contains(" -> ") && line.contains("][R]");
    }

    public static boolean isFreecube(String line) {
        return line.contains(" -> ") && line.contains("]") && line.split(" -> ")[1].split("]")[0].matches("([A-D][0-9]{1,6})");
    }

    public static boolean isParty(String line) {
        return line.contains("[Groupe] ") && line.contains(": &");
    }

    public static boolean isStaffChat(String line) {
        return line.startsWith("[Staff]") || line.startsWith("[Modo]") || line.startsWith("[SuperModo]");
    }

    public static String parseGlobal(String line) {
        return line.toLowerCase();
    }

    public static String parseChat(String line) {
        if (isChat(line)) {
            if (line.contains(": @"))
                return ArraysUtils.split(line, ": @")[1].toLowerCase();
            return ArraysUtils.split(line, ": ")[1].toLowerCase();
        }
        if (isFreecube(line))
            return ArraysUtils.split(line, "] ")[1].toLowerCase();
        return null;
    }

    public static String parsePrivateMessage(String line) {
            return ArraysUtils.split(line, "]")[1].replace("[R]", "").toLowerCase();
    }

    public static String parseParty(String line) {
            return ArraysUtils.split(line, ": &")[1].toLowerCase();
    }


    public static String parseStaff(String line) {
            return ArraysUtils.split(line, ": ")[1].toLowerCase();
    }
}
