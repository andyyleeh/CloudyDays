package me.andrewhanselee.cloudydays;

import android.net.Uri;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Andrew on 2017-01-18.
 */

public class JsonParse {

    public static URL getWeather (String location, String format, String units, String numDays){
        //build url
        final String BASE_URL =  "http://api.openweathermap.org/data/2.5/forecast/daily?";

        final String QUERY = "q";
        final String FORMAT = "mode";
        final String UNITS = "units";
        final String DAYS = "cnt";
        final String API = "appid";
        final String KEY = "c17da12720f3655193723e842abe2a79";

        Uri buildUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY, location)
                .appendQueryParameter(FORMAT, format)
                .appendQueryParameter(UNITS, units)
                .appendQueryParameter(DAYS, numDays)
                .appendQueryParameter(API,KEY)
                .build();

        URL finalUrl = null;
        try{
            finalUrl = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return finalUrl;

    }

    public static String getResponse (URL url)throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean input = scanner.hasNext();
            if (input) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
