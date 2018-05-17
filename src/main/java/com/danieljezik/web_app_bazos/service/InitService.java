package com.danieljezik.web_app_bazos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties("init")
public class InitService {
    private boolean populateDB;

    @Autowired
    private DataHandler dataHandler;

    @PostConstruct
    public void init() {
        if (isPopulateDB()) {
            try {
                dataHandler.getWebContent(15);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isPopulateDB() {
        return populateDB;
    }

    public void setPopulateDB(boolean populateDB) {
        this.populateDB = populateDB;
    }
}
