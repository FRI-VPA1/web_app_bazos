package com.danieljezik.web_app_bazos.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class DataMiner {

    /**
     * Vracia String obsahujúci HTML kód stránky zadanej ako parameter URL
     *
     * @param url URL adresa, z ktorej HTML kód chceme
     * @return požadovaný HTML kód
     * @throws IOException IOException
    */

    public String getHtmlContent(String url) throws Exception, IOException {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        String html = "";
        InputStream in = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder str = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            str.append(line);
            str.append("\n");
        }
        in.close();
        html = str.toString();

        return html;
    }
}
