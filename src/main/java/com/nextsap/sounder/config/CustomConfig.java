package com.nextsap.sounder.config;

import java.util.HashMap;
import java.util.Map;

/**
 * An object class 'CustomConfig'
 */
public class CustomConfig {

    // Define attribute
    private Map<String, CustomFilter> customFilters;

    /**
     * {@link CustomConfig} constructor
     * @param customFilters a {@link Map} that contains the name and the {@link CustomFilter}
     */
    public CustomConfig(Map<String, CustomFilter> customFilters) {
        this.customFilters = customFilters;
    }

    /**
     * {@link CustomConfig} constructor
     */
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
