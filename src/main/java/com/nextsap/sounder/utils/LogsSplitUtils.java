package com.nextsap.sounder.utils;

import java.util.regex.Pattern;
/*
    A class to split AzLauncher chat logs
 */
public class LogsSplitUtils {

    /**
     * The minecraft player name pattern
     */
    private static final String playerPattern = Pattern.compile("([a-zA-Z0-9_]{3,16})").pattern();

    /**
     *  Check if the log is a chat log
     *
     * @param line the log
     * @return {@link Boolean}
     */
    public static boolean isChat(String line) {
        if (!line.contains(": @") && !line.contains(": ")) return false;
        String pseudo = line.split(":")[0].split(" ")[line.split(":")[0].split(" ").length - 1];
        return pseudo.matches(playerPattern);
    }

    /**
     * Check if the log is a private message log
     *
     * @param line the log
     * @return {@link Boolean}
     */
    public static boolean isPrivateMessage(String line) {
        if (!line.contains(" -> Moi][R] ")) return false;
        String pseudo = line.split(" ")[1];
        return pseudo.matches(playerPattern);
    }

    /**
     * Check if the log is a freecube log
     *
     * @param line the log
     * @return {@link Boolean}
     */
    public static boolean isFreecube(String line) {
        if (line.contains("[") && line.contains(" -> ") && line.contains("] ")) {
            String pseudo = line.split(" ")[1];
            String zone = line.split(" -> ")[1].split("] ")[0];

            return pseudo.matches(playerPattern) && (zone.matches("([A-D][0-9]{1,6})") || zone.equals("Spawn"));
        }
        return false;
    }

    /**
     * Check if the log is a game chat log
     *
     * @param line the log
     * @return {@link Boolean}
     */
    public static boolean isGameChat(String line) {
        if (line.contains("[") && line.contains(" -> ") && line.contains("] ")) {
            String pseudo = line.split(" ")[1];
            String team = line.split(" -> ")[1].split("] ")[0];
            return pseudo.matches(playerPattern) && isTeam(team);
        }
        return false;
    }

    /**
     * Check if the log is a team log
     *
     * @param line the log
     * @return {@link Boolean}
     */
    public static boolean isTeam(String line) {
        return line.equals("Rouge") || line.equals("Orange") || line.equals("Jaune") || line.equals("Vert") ||
                line.equals("Bleu") || line.equals("Rose") || line.equals("Violet") || line.equals("Gris") ||
                line.equals("Noir") || line.equals("Blanc") || line.equals("Spectateur");
        // TODO: Survival Teams
    }

    /**
     * Check if the log is a party log
     *
     * @param line the log
     * @return {@link Boolean}
     */
    public static boolean isParty(String line) {
        if (!line.contains(" [Groupe] ") && !line.contains(": &")) return false;
        String pseudo = line.split(":")[0].split(" ")[line.split(":")[0].split(" ").length - 1];
        return pseudo.matches(playerPattern);
    }

    /**
     * Check if the log is a staff chat log
     *
     * @param line the log
     * @return {@link Boolean}
     */
    public static boolean isStaffChat(String line) {
        if (!line.startsWith("[Staff]") || !line.startsWith("[Modo]") || !line.startsWith("[SuperModo]") || !line.startsWith("[Admin]"))
            return false;
        String pseudo = line.split(":")[0].split(" ")[line.split(":")[0].split(" ").length - 1];
        return pseudo.matches(playerPattern);
    }

    /**
     * Parse a global log
     *
     * @param line the log
     * @return {@link String} parsed
     */
    public static String parseGlobal(String line) {
        return line.toLowerCase();
    }

    /**
     * Parse a chat log
     *
     * @param line the log
     * @return {@link String} parsed
     */
    public static String parseChat(String line) {
        if (isFreecube(line) || isGameChat(line))
            return " " + ArraysUtils.split(line, "] ")[1].toLowerCase();
        if (isChat(line)) {
            if (line.contains(": @"))
                return " " + ArraysUtils.split(line, ": @")[1].toLowerCase();
            return " " + ArraysUtils.split(line, ": ")[1].toLowerCase();
        }
        return null;
    }

    /**
     * Parse a private message log
     *
     * @param line the log
     * @return {@link String} parsed
     */
    public static String parsePrivateMessage(String line) {
        return " " + ArraysUtils.split(line, "]")[1].replace("[R]", "").toLowerCase();
    }

    /**
     * Parse a party log
     *
     * @param line the log
     * @return {@link String} parsed
     */
    public static String parseParty(String line) {
        return " " + ArraysUtils.split(line, ": &")[1].toLowerCase();
    }

    /**
     * Parse a staff chat log
     *
     * @param line the log
     * @return {@link String} parsed
     */
    public static String parseStaff(String line) {
        return " " + ArraysUtils.split(line, ": ")[1].toLowerCase();
    }
}
