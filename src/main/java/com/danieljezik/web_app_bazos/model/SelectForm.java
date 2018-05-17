package com.danieljezik.web_app_bazos.model;

public class SelectForm {

    private String selected_model;
    private String selected_capacity;
    private String selected_colour;

    /**
     * Trieda pre odosielací formulár
     *
     * @return formulár
     */

    public String getSelected_model() {
        return selected_model;
    }

    public void setSelected_model(String selected_model) {
        this.selected_model = selected_model;
    }

    public String getSelected_capacity() {
        return selected_capacity;
    }

    public void setSelected_capacity(String selected_capacity) {
        this.selected_capacity = selected_capacity;
    }

    public String getSelected_colour() {
        return selected_colour;
    }

    public void setSelected_colour(String selected_colour) {
        this.selected_colour = selected_colour;
    }
}
