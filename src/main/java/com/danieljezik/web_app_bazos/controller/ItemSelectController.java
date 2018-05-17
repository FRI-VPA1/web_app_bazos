package com.danieljezik.web_app_bazos.controller;

import com.danieljezik.web_app_bazos.model.SelectForm;

import com.danieljezik.web_app_bazos.config.ItemsSelectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemSelectController {

    // nastavi sa cez autowired konstruktor
    private ItemsSelectConfig itemsSelectConfig;

    @Autowired
    public ItemSelectController(ItemsSelectConfig itemsSelectConfig) {
        this.itemsSelectConfig = itemsSelectConfig;
    }

    private String selected_model,selected_colour,selected_capacity;

    /**
     * Route - ktorý má namapovanú default adresu teda napr. http://localhost:8080/
     * Pri požiadavke GET na daný route vráti View -> home.html s danými atribútmi
     * @param model model viewu obsahujúci atribúty
     * @return  vracia požadovaný View -> home.html
     */
    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("models", itemsSelectConfig.getModels());
        model.addAttribute("capacity", itemsSelectConfig.getCapacity());
        model.addAttribute("colour", itemsSelectConfig.getColour());
        model.addAttribute("selectForm", new SelectForm());
        return "home/home";
    }

    public String getSelected_model() {
        return selected_model;
    }

    public void setSelected_model(String selected_model) {
        this.selected_model = selected_model;
    }

    public String getSelected_colour() {
        return selected_colour;
    }

    public void setSelected_colour(String selected_colour) {
        this.selected_colour = selected_colour;
    }

    public String getSelected_capacity() {
        return selected_capacity;
    }

    public void setSelected_capacity(String selected_capacity) {
        this.selected_capacity = selected_capacity;
    }
}
