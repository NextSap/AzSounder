package com.nextsap.sounder.config;

import java.util.HashMap;
import java.util.Map;

public class CustomConfig {

    private Map<String, CustomFilter> customFilters;

    public CustomConfig(Map<String, CustomFilter> customFilters) {
        this.customFilters = customFilters;
    }

    public CustomConfig() {
        this.customFilters = new HashMap<>();
    }

    public void addFilter(String name, CustomFilter customFilter) {
        this.customFilters.put(name, customFilter);
    }

    public Map<String, CustomFilter> getCustomFilters() {
        return customFilters;
    }

    public void setCustomFilters(Map<String, CustomFilter> customFilters) {
        this.customFilters = customFilters;
    }

    public void removeFilter(String name) {
        this.customFilters.remove(name);
    }

    public void removeFilter(String name, CustomFilter customFilter) {
        this.customFilters.remove(name, customFilter);
    }
}
