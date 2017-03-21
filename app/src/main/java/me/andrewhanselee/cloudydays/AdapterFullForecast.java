package me.andrewhanselee.cloudydays;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Andrew on 2017-01-26.
 */

public class AdapterFullForecast extends RecyclerView.Adapter<AdapterFullForecast.AdapterFullViewHolder>{
    public AdapterFullForecast(){}
    private String[] wData;

    public class AdapterFullViewHolder extends RecyclerView.ViewHolder {
        public final TextView mWeatherText;
        public final ImageView mImage;

        public AdapterFullViewHolder(View itemView) {
            super(itemView);
            mWeatherText = (TextView) itemView.findViewById(R.id.tv_weather);
            mImage = (ImageView) itemView.findViewById(R.id.image_weather);
            int color = Color.parseColor("#FFFFFF");
            mImage.setColorFilter(color);
        }
    }

    @Override
    public AdapterFullViewHolder onCreateViewHolder (ViewGroup viewGr, int viewType){
        Context context = viewGr.getContext();
        int layoutID = R.layout.forecast_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutID, viewGr, false);
        return new AdapterFullViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterFullViewHolder forecastAdapterViewHolder, int pos) {
        String day = wData[pos];
        forecastAdapterViewHolder.mWeatherText.setText(day);
        if(day.indexOf("snow") != -1){
            forecastAdapterViewHolder.mImage.setImageResource(R.drawable.snow);
        } else if(day.indexOf("cloud") != -1){
            forecastAdapterViewHolder.mImage.setImageResource(R.drawable.cloud);
        } else if(day.indexOf("rain") != -1){
            forecastAdapterViewHolder.mImage.setImageResource(R.drawable.rain);
        } else if(day.indexOf("hail") != -1){
            forecastAdapterViewHolder.mImage.setImageResource(R.drawable.hail);
        } else if(day.indexOf("wind") != -1){
            forecastAdapterViewHolder.mImage.setImageResource(R.drawable.windy);
        } else if(day.indexOf("sun") != -1){
            forecastAdapterViewHolder.mImage.setImageResource(R.drawable.sunny);
        } else {
            forecastAdapterViewHolder.mImage.setImageResource(R.drawable.sunny);
        }
    }

    @Override
    public int getItemCount() {
        if (null == wData) return 0;
        return wData.length;
    }

    public void setWeatherData(String[] weatherData) {
        wData = weatherData;
        notifyDataSetChanged();
    }
}




