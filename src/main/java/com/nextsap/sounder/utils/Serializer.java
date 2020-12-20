package com.nextsap.sounder.utils;

import com.google.gson.Gson;
/*
    A class to manage JSON
 */
public class Serializer {

    /**
     * Serialize an {@link Object}
     *
     * @param object the {@link Object} to serialize
     * @return a {@link String} json
     */
    public static String serialize(Object object) {
        // TODO: Format the json to have a better file
        return new Gson().toJson(object);
    }

    /**
     * Deserialize an {@link Object}
     *
     * @param json the {@link String} json
     * @param _class the class object
     * @return an {@link Object}
     */
    public static Object deserialize(String json, Class<?> _class) {
        try {
            return new Gson().fromJson(json, _class);
        } catch (Exception exception) {
            return null;
        }
    }
}
