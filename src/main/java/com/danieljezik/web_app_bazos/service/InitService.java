package com.danieljezik.web_app_bazos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitService {

    @Autowired
    private DataHandler dataHandler;

    /**
     * Metóda pri inicializácii projektu načíta zavolá metódu -> getWebContent a načíta požadovaný obsah
     */
    /*
    @PostConstruct
    public void init() {
        try {
            dataHandler.getWebContent(15);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

}
