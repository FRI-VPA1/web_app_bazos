package com.danieljezik.web_app_bazos.database;

import com.danieljezik.web_app_bazos.model.Item;
import com.danieljezik.web_app_bazos.model.SelectForm;
import com.danieljezik.web_app_bazos.config.DBConnectionConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
@ConfigurationProperties("dbhandler")
public class DBHandler {

    public static final Logger log = LoggerFactory.getLogger(DBHandler.class.getName());

    private String[] filter;
    private Connection dbConnection;
    private DBConnectionConfig dbConfig;

    @Autowired
    public DBHandler(Connection dbConnection, DBConnectionConfig dbConfig) {
        this.dbConnection = dbConnection;
        this.dbConfig = dbConfig;
    }

    /**
     * Metóda pre vkaldanie Itemov do databázy
     *
     * @param arrayList
     */
    public void insertDataToDB(ArrayList<Item> arrayList) {
        String title = "";
        String link = "";
        String price = "";
        try {
            for (int i = 0; i < arrayList.size(); i++)
            {
                if (StringUtils.isNumeric(arrayList.get(i).price)) {
                    title = arrayList.get(i).title;
                    link = arrayList.get(i).link;
                    price = arrayList.get(i).price;

                    String insertQueryStatement = "INSERT  INTO  item  VALUES  (?,?,?)";

                    PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQueryStatement);
                    preparedStatement.setString(1, title);
                    preparedStatement.setString(2, link);
                    preparedStatement.setString(3, price);

                    // execute insert SQL statement
                    preparedStatement.executeUpdate();
                    log.info(title + " <-- added successfully");
                }
            }
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metóda pre získavanie všetkých Item podľa zadaných selektov zo SelectFormu
     *
     * @param selectForm
     * @return Arraylist vyhovujúcich Itemov
     */
    public ArrayList<Item> getDataFromDB(SelectForm selectForm) {
        ArrayList<Item> arrayListOfItems = new ArrayList<>();
        log.info("name " + dbConfig.getUsername());
        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM item WHERE MATCH (title) AGAINST ( ? IN BOOLEAN MODE)";
            String QUERY = "+iphone ";
            QUERY += "+" + selectForm.getSelected_model();

            if (!selectForm.getSelected_capacity().equals("NEZÁLEŽÍ"))
            {
                QUERY += " +" + selectForm.getSelected_capacity() + "*";
            }

            if (!selectForm.getSelected_colour().equals("NEZÁLEŽÍ"))
            {
                QUERY += " +" + selectForm.getSelected_colour();
            }

            for (int i = 0; i < filter.length; i++)
            {
                QUERY += " -" + filter[i];
                i++;
            }

            PreparedStatement preparedStatement = dbConnection.prepareStatement(getQueryStatement);
            preparedStatement.setString(1, QUERY);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = preparedStatement.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                arrayListOfItems.add(
                        new Item(
                                rs.getString("title"),
                                rs.getString("price"),
                                rs.getString("link")
                        ));
            }

        } catch (

                SQLException e) {
            e.printStackTrace();
        }
        return arrayListOfItems;

    }

    public String[] getFilter() {
        return filter;
    }

    public void setFilter(String[] filter) {
        this.filter = filter;
    }
}