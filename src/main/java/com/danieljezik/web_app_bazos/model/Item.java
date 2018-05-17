package com.danieljezik.web_app_bazos.model;

import java.io.Serializable;

public class Item implements Serializable {
    public String title;
    public String price;
    public String link;

    /**
     * Objekty riedy Item predstavujúci vytvorený objekt
     * po vykonaní všetkých metód
     *
     * @param title Nadpis / popis (inzerátu/objektu)
     * @param price Cena
     * @param link  Link na inzerát
     */
    public Item(String title, String price, String link) {
        this.title = title;
        this.price = price;
        this.link = link;
    }
}
