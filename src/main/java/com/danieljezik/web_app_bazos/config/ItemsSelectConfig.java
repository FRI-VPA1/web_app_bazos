package com.danieljezik.web_app_bazos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("items.select")
public class ItemsSelectConfig {

    private String[] models;
    private String[] capacity;
    private String[] colour;

    public String[] getModels() {
        return models;
    }

    public void setModels(String[] models) {
        this.models = models;
    }

    public String[] getCapacity() {
        return capacity;
    }

    public void setCapacity(String[] capacity) {
        this.capacity = capacity;
    }

    public String[] getColour() {
        return colour;
    }

    public void setColour(String[] colour) {
        this.colour = colour;
    }
}
