package com.nextsap.sounder.config;

public enum Options {

    GLOBAL("Global"),
    CHAT("Chat"),
    PRIVATE_MESSAGE("Message privé"),
    PARTY("Chat de Groupe"),
    STAFF_CHAT("Chat du Staff");

    private final String name;

    Options(String name) {
        this.name = name;
    }

    public static Options getOptionByName(String name) {
        for (Options o : Options.values()) {
            if (o.getName().equalsIgnoreCase(name))
                return o;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
