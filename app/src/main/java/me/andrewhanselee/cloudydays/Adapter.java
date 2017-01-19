package me.andrewhanselee.cloudydays;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Andrew on 2017-01-18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterView> {

    private String[] weatherdata;

    public Adapter(){

    }
    public class AdapterView extends RecyclerView.ViewHolder{

        public TextView weatherText;
        public AdapterView(View view){
            super(view);
            weatherText = (TextView) view.findViewById(R.id.tv_weather);
        }

    }

    public void setData(String[] wData){
        weatherdata = wData;
    }

    @Override
    public AdapterView onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        boolean stick = false;
        int layout = R.layout.forecast_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,viewGroup,stick);
        return new AdapterView(view);
    }

    @Override
    public int getItemCount() {
        if (null == weatherdata) return 0;
        return weatherdata.length;
    }

    @Override
    public void onBindViewHolder(AdapterView adapterView, int position) {
        String weatherForThisDay = weatherdata[position];
        adapterView.weatherText.setText(weatherForThisDay);
    }

}
