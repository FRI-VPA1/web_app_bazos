package com.danieljezik.web_app_bazos.controller;

import com.danieljezik.web_app_bazos.model.SelectForm;
import com.danieljezik.web_app_bazos.model.Item;
import com.danieljezik.web_app_bazos.database.DBHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HomeController {
    private ArrayList<Item> items;

    private DBHandler dbHandler;

    @Autowired
    public HomeController(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    /**
     * Route - ktorý je namapovaný na http://localhost:8080/home
     * Pri GET - REQUESTE vracia SelectForm
     * @param model model metody
     * @return  new Selectform
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public SelectForm index(Model model){
        return new SelectForm();
    }

    /**
     * Route - ktorý je namapovaný na http://localhost:8080/home
     * Pri POST - REQUESTE vracia view, ktorý obsahuje data na zaklade selectov z view -> home.html
     * Databáza vracia požadované dáta a tie sa vo viewe zobrazujú vo forme grafu a okien
     *
     * @param selectForm select form z predošlého viewu ( s užívateľským výberom )
     * @param model model viewu
     * @return index.html
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String getSelectedForm(@ModelAttribute SelectForm selectForm, Model model)
    {
        items = new ArrayList<Item>(dbHandler.getDataFromDB(selectForm));

        if (items.size() == 0){
            return "home/error";
        }
        model.addAttribute("prices", getItemsPrices(items));
        model.addAttribute("titles", getItemsTitles(items));
        model.addAttribute("min", getMinValue(items));
        model.addAttribute("max", getMaxValue(items));
        model.addAttribute("avg", getAverage(items));
        model.addAttribute("avg", getAverage(items));
        return "home/index";
    }

    /**
     * Vracia najmenšiu hodnotu z Arraylistu Itemov na základe ceny
     *
     * @param items arraylist itemov
     * @return  najnižšia cena
     */
    private int getMinValue(ArrayList<Item> items)
    {
        int smallest = Integer.parseInt(items.get(0).price);
        for(Item item : items ){
            int tmp = Integer.parseInt(item.price);
            if (tmp < smallest) {
                smallest = tmp;
            }
        }
        return smallest;
    }

    /**
     * Vracia najvyššiu hodnotu z Arraylistu Itemov na základe ceny
     *
     * @param items arraylist itemov
     * @return  najvyššia cena
     */
    private int getMaxValue(ArrayList<Item> items)
    {
        int biggest = Integer.parseInt(items.get(0).price);
        for(Item item : items ){
            int tmp = Integer.parseInt(item.price);
            if (tmp > biggest) {
                biggest = tmp;
            }
        }
        return biggest;
    }

    /**
     * Vracia priemernú hodnotu z Arraylistu Itemov na základe ceny
     *
     * @param items arraylist itemov
     * @return  priemerná cena
     */
    private int getAverage(ArrayList<Item> items) {
        Integer sum = 0;
            for (Item item : items ) {
                int tmp = Integer.parseInt(item.price);
                sum += tmp;
            }
            return sum.intValue() / items.size();
    }

    private ArrayList getItemsPrices(ArrayList<Item> items)
    {
        ArrayList<Integer> prices = new ArrayList<>();
        for (Item item : items)
        {
            prices.add(Integer.parseInt(item.price));
        }
        return prices;
    }

    private ArrayList getItemsTitles(ArrayList<Item> items)
    {
        ArrayList<String> titles = new ArrayList<>();
        for (Item item : items)
        {
            titles.add(item.title);
        }
        return titles;
    }

}
