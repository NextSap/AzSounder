package com.nextsap.sounder.utils;

public class ArraysUtils {

    public static String[] split(String toSplit, String split) {
        String[] parts = toSplit.split(split);
        int splitLength = split.length();

        StringBuilder rest = new StringBuilder();

        for (int i = 1; i < parts.length; i++)
            rest.append(parts[i]).append(split);

        return new String[]{parts[0], rest.toString().substring(0, rest.toString().length() - splitLength)};
    }
}
