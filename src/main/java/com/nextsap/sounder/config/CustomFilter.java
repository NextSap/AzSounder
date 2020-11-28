package com.nextsap.sounder.config;

public class CustomFilter {

    private String sound_path;

    private Options options;

    private boolean active;

    public CustomFilter(String sound_path, Options options, boolean active) {
        this.sound_path = sound_path;
        this.options = options;
        this.active = active;
    }

    public CustomFilter() {
        this.sound_path = "";
        this.options = Options.GLOBAL;
        this.active = true;
    }

    public String getSound_path() {
        return sound_path;
    }

    public void setSound_path(String sound_path) {
        this.sound_path = sound_path;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public boolean isActive() {
        return active;
    }

    public String getActive() {
        if (active) return "ACTIVÉ";
        return "DÉSACTIVÉ";
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOptionByName(String name) {
        this.options = Options.getOptionByName(name);
    }
}
