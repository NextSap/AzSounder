package com.nextsap.sounder.config;

public class CustomDetection {

    private String sound_path;

    private Option option;

    public CustomDetection(String sound_path, Option option) {
        this.sound_path = sound_path;
        this.option = option;
    }

    public CustomDetection() {
        this.sound_path = "";
        this.option = Option.GLOBAL;
    }

    public String getSound_path() {
        return sound_path;
    }

    public void setSound_path(String sound_path) {
        this.sound_path = sound_path;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setOptionByName(String name) {
        this.option = Option.getOptionByName(name);
    }
}
