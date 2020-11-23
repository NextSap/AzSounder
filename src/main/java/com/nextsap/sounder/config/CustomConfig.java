package com.nextsap.sounder.config;

import java.util.HashMap;
import java.util.Map;

public class CustomConfig {

    private Map<String, CustomDetection> customDetections;

    public CustomConfig(Map<String, CustomDetection> customDetections) {
        this.customDetections = customDetections;
    }

    public CustomConfig() {
        this.customDetections = new HashMap<>();
    }

    public Map<String, CustomDetection> getCustomDetections() {
        return customDetections;
    }

    public void setCustomDetections(Map<String, CustomDetection> customDetections) {
        this.customDetections = customDetections;
    }

    public void addCustomDetection(String name, CustomDetection customDetection) {
        this.customDetections.put(name, customDetection);
    }

    public void removeCustomDetection(String name) {
        this.customDetections.remove(name);
    }

    public void removeCustomDetection(String name, CustomDetection customDetection) {
        this.customDetections.remove(name, customDetection);
    }
}
