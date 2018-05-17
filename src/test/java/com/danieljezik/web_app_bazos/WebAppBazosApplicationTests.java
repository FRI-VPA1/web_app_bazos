package com.danieljezik.web_app_bazos;

import com.danieljezik.web_app_bazos.config.DBConnectionConfig;
import com.danieljezik.web_app_bazos.config.ItemsSelectConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebAppBazosApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(SpringRunner.class.getName());

    @Autowired
    private DBConnectionConfig dbConfig;

    @Autowired
    private ItemsSelectConfig itemsSelectConfig;

    @Test
    public void contextLoads() {

    }

    @Test
    public void printConfigValues() {
        log.info("--------------- dbConfig:");
        log.info(dbConfig.getJdbc());
        log.info(dbConfig.getPassword());
        log.info(dbConfig.getUrl());
        log.info(dbConfig.getUsername());

        log.info("--------------- itemsSelectConfig:");
        log.info(itemsSelectConfig.getCapacity().toString());
        log.info(itemsSelectConfig.getColour().toString());
        log.info(itemsSelectConfig.getModels().toString());
    }

}
