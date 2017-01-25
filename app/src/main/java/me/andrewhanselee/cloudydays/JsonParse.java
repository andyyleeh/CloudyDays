package me.andrewhanselee.cloudydays;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrew on 2017-01-19.
 */

public class JsonParse {
    public static String[] parsedData(Context context, String JsonString) throws JSONException{
        final String CITY = "name";
        String[] parsed = null;

        JSONObject forecast = new JSONObject(JsonString);
        JSONArray forecastArray = forecast.getJSONArray("list");

        parsed = new String[forecastArray.length()];

        JSONObject cityInfo= forecast.getJSONObject("city");

        String forecastCity = cityInfo.getString(CITY);

        String description;

        JSONObject current = forecastArray.getJSONObject(0);
        JSONArray main = current.getJSONArray("weather");
        JSONObject obj = main.getJSONObject(0);
        description = obj.getString("description");



        parsed[0] = forecastCity + "-" + description;


        return parsed;
    }
}
