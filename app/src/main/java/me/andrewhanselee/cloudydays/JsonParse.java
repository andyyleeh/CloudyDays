package me.andrewhanselee.cloudydays;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.provider.*;
import android.provider.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.andrewhanselee.cloudydays.NumberFormatting.DateFormat;
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
        long localtime = System.currentTimeMillis();
        long utctime = DateFormat.getUTCFromLocal(localtime);
        long startTime = DateFormat.normalizeDate(utctime);

        for(int i = 0; i < forecastArray.length(); i++){
            String date;
            String highNlow;
            long timeMillis;
            String info;
            double high;
            double low;
            JSONObject weather = forecastArray.getJSONObject(i);

            timeMillis = startTime + DateFormat.day_in_milli * i;
            date = DateFormat.easyTime(context,timeMillis, false);
            JSONObject wobject = weather.getJSONObject("main");
            info = weather.getJSONArray("weather").getJSONObject(0).getString("description"); //decription
            high = wobject.getDouble("temp_max");
            low = wobject.getDouble("temp_min");
            highNlow = TempFormat.formatHL(context,high,low);
            parsed[i] = date + " - " + info + " - " + highNlow;
        }


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
        String descrip = description.substring(0, 1).toUpperCase() + description.substring(1);



        parsed = descrip + "\n" + now +  " \n" + hL;


        return parsed;
    }
}
