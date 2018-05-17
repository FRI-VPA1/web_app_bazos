package com.danieljezik.web_app_bazos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties("init")
public class InitService {
    private boolean populateDB;
    private int pages;

    @Autowired
    private DataHandler dataHandler;

    @PostConstruct
    public void init() {
        if (isPopulateDB()) {
            try {
                dataHandler.getWebContent(getPages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isPopulateDB() {
        return populateDB;
    }

    public void setPopulateDB(boolean populateDB) {
        this.populateDB = populateDB;
    }
}
