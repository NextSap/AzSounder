package com.nextsap.sounder.config;

public enum Option {

    GLOBAL("Global"),
    CHAT("Chat"),
    PRIVATE_MESSAGE("Private message"),
    PARTY("Party Chat"),
    STAFF_CHAT("Staff Chat");

    private final String name;

    Option(String name) {
        this.name = name;
    }

    public static Option getOptionByName(String name) {
        for (Option o : Option.values()) {
            if (o.getName().equalsIgnoreCase(name))
                return o;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
