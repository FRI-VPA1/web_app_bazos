package com.danieljezik.web_app_bazos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@ConfigurationProperties(prefix = "bazos.database")
public class DBConnectionConfig {

    private String jdbc;
    private String url;
    private String username;
    private String password;

    public String getJdbc() {
        return jdbc;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setJdbc(String jdbc) {
        this.jdbc = jdbc;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metóda pre získanie Connection
     *
     * @return Connection
     * @throws SQLException SQLException
     */
    @Bean
    public Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(
                url,
                username,
                password
        );
    }

}
