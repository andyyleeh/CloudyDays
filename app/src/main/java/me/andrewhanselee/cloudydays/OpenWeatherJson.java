package me.andrewhanselee.cloudydays;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrew on 2017-01-18.
 */

public class OpenWeatherJson {

    public static String[] weatherJsonData (Context context, String JsonString) throws JSONException{

            String[] parsedData = null;
            JSONObject forecastJson = new JSONObject(JsonString);

            // error codes?

            JSONArray weatherArray = forecastJson.getJSONArray("list");

            parsedData = new String[weatherArray.length()];

            return parsedData;
        }
    }

