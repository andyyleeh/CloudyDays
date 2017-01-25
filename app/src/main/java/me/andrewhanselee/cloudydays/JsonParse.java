package me.andrewhanselee.cloudydays;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.andrewhanselee.cloudydays.NumberFormatting.TempFormat;

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

    public static String parsedDay (Context context, String JsonString) throws JSONException{
        final String CITY = "name";
        String parsed = null;

        JSONObject forecast = new JSONObject(JsonString);
        JSONArray forecastArray = forecast.getJSONArray("list");

        JSONObject cityInfo= forecast.getJSONObject("city");

        String forecastCity = cityInfo.getString(CITY);

        String description;

        JSONObject today = forecastArray.getJSONObject(0);
        JSONArray weather = today.getJSONArray("weather");
        JSONObject main = today.getJSONObject("main");
        double currentT = main.getDouble("temp");
        String now = TempFormat.formatTemp(context, currentT);
        double max = main.getDouble("temp_max");
        double min = main.getDouble("temp_min");
        String hL = TempFormat.formatHL(context, max, min);


        JSONObject obj = weather.getJSONObject(0);
        description = obj.getString("description");



        parsed = forecastCity + "-" + description + "\n" + now + " \n" + hL;


        return parsed;
    }
}
