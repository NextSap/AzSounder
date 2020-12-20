package com.nextsap.sounder.utils;
/*
    A class to manage arrays more simply
 */
public class ArraysUtils {

    /**
     * This splits only once a string
     *
     * @param toSplit the {@link String} to split
     * @param split the {@link String} spliter
     * @return a {@link String[]} that contains the first split et the rest of the array
     */
    public static String[] split(String toSplit, String split) {
        String[] parts = toSplit.split(split);
        int splitLength = split.length();

        StringBuilder rest = new StringBuilder();

        for (int i = 1; i < parts.length; i++)
            rest.append(parts[i]).append(split);

        return new String[]{parts[0], rest.substring(0, rest.toString().length() - splitLength)};
    }
}
