package me.andrewhanselee.cloudydays;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Andrew on 2017-01-26.
 */

public class AdapterFullForecast extends RecyclerView.Adapter<AdapterFullForecast.AdapterFullViewHolder>{
    public AdapterFullForecast(){}
    private String[] wData;

    public class AdapterFullViewHolder extends RecyclerView.ViewHolder {
        public final TextView mWeatherText;

        public AdapterFullViewHolder(View itemView) {
            super(itemView);
            mWeatherText = (TextView) itemView.findViewById(R.id.tv_weather);
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




