package me.andrewhanselee.cloudydays;

import android.content.Context;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Andrew on 2017-01-19.
 */

public class Network {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?";
    private static String FORMAT = "json";
    static String UNITS = "metric";
    private static int NUM_DAYS = 7;
    final static String QUERY_PARAM = "q";
    final static String LAT_PARAM = "lat";
    final static String LON_PARAM = "lon";
    final static String FORMAT_PARAM = "mode";
    final static String UNITS_PARAM = "units";
    final static String DAYS = "cnt";
    final static String API = "appid";
    final static String KEY = "c17da12720f3655193723e842abe2a79";

    public static URL getURL(String location){
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, location)
                .appendQueryParameter(FORMAT_PARAM, FORMAT)
                .appendQueryParameter(UNITS_PARAM, UNITS)
                .appendQueryParameter(DAYS, Integer.toString(NUM_DAYS))
                .appendQueryParameter(API, KEY)
                .build();
        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch(MalformedURLException e){
            e.printStackTrace();
        }
        return url;

    }


    public static String getResponse(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }
}

