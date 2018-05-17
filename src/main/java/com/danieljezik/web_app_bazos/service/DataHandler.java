package com.danieljezik.web_app_bazos.service;

import com.danieljezik.web_app_bazos.database.DBHandler;
import com.danieljezik.web_app_bazos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataHandler {

    public static ArrayList<Item> arrayOfItems = new ArrayList<>() ;

    // nastavene pomocou dependeny injectora
    public DataMiner dataMiner;
    public DBHandler dbHandler;

    @Autowired
    public DataHandler(DataMiner dataMiner, DBHandler dbHandler) {
        this.dataMiner = dataMiner;
        this.dbHandler = dbHandler;
    }

    /**
     * Metóda, ktorá za náklade vstupného parametru prechádza daný počet stránok inzercie,
     * na základe parametru pageCount potom zhotovuje URL, z ktorej sťahuje HTML kód. HTML kód
     * sa ďalej spracuje v ďalšej metóde, výsledokm tejto metódy je pole objektov Item
     *
     * @param pageCount celočíselný počet stránok pre načítanie a rozparsovanie
     * @throws Exception zachýtavanie prípadnej výnimky
     */
    public void getWebContent(int pageCount) throws Exception {
        String URL = "https://mobil.bazos.sk/apple/";
        String rootURL = URL;
        String contentHTML;

        if ((pageCount > 0) && (pageCount < 20))
        {
            for (int i = 1; i <= pageCount; i++)
            {
                if (i != 1)
                {
                    URL = rootURL + (i * 20) + "/";
                }
                contentHTML = dataMiner.getHtmlContent(URL);
                scrapeBazos(contentHTML);
                URL = "";
            }
        }
        //todo: tu to vzdy padne kvoli null pointer exception
        dbHandler.insertDataToDB(arrayOfItems);
    }

    /**
     * Metóda scrapeBazos dostáva ako parameter source.Source je HTML kód stránky, ktorý bude rozparsovaný touto metódou,
     * pre parsovanie používa regulárne výrazy. Výrazy v regulárnych výrazoch sú odchytávané do skupín, odkiaľ tieto dáta
     * vyberáme a vytvárame z nich objekty typu Item
     *
     * @param $source je premenná obsahujúca HTML kód stránky pripravený na rozparsovanie
     */
    public void scrapeBazos(String $source){
        Pattern p_title_link = Pattern.compile("<span class=nadpis><a (?:.*?)href=\"(.+)\">(.+)<\\/a><\\/span>");
        Pattern p_price = Pattern.compile("<span class=cena><b>([^`]*?)<\\/b><\\/span>");
        Matcher m_title_link = p_title_link.matcher($source);
        Matcher m_price = p_price.matcher($source);

        while(m_title_link.find() && m_price.find()) {
            arrayOfItems.add(new Item(
                    m_title_link.group(2),
                    m_price.group(1).trim().substring(0,m_price.group(1).trim().length()-2),
                    "https://mobil.bazos.sk" + m_title_link.group(1)
            ));
        }
    }
}
