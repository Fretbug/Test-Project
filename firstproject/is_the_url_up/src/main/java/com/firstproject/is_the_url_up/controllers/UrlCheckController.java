package com.firstproject.is_the_url_up.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private static final String SITE_IS_UP = "SITE IS UP";
    private static final String SITE_IS_DOWN = "SITE IS DOWN";
    private static final String INCORRECT_SITE = "SITE IS INCORECT";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url)
    {
        String returnMessage = "";
        try {
            URL urlObject = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int resposeCodeCategory = (conn.getResponseCode()) / 100;
                if (resposeCodeCategory !=2 || resposeCodeCategory !=3)
                {
                    returnMessage = SITE_IS_DOWN;
                }
                else
                {
                    returnMessage = SITE_IS_UP;
                }
            }
        catch (MalformedURLException e) {
            returnMessage = INCORRECT_SITE;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }
}
