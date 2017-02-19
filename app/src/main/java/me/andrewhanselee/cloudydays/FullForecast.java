package me.andrewhanselee.cloudydays;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;

public class FullForecast extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mErrorMsg;
    private AdapterFullForecast mAdapter;
    private ProgressBar mPBFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
        setContentView(R.layout.activity_full_forecast);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_forecast);
        mErrorMsg = (TextView) findViewById(R.id.tv_loading_Error);

        boolean reverse = false;

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, reverse);
        mRecyclerView.setLayoutManager(layoutManager);
         // set layout manager for recycler view to linear layout

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new AdapterFullForecast();
        mRecyclerView.setAdapter(mAdapter);
        mPBFull = (ProgressBar) findViewById(R.id.pb_fullLoad);

        //set adapter to recycler view
        showWeather();
    }
    private String city = "montreal, ca";


    public void showWeather() {

        new FullForecast.getData().execute(city);
    }

    public class getData extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPBFull.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            String bob = params[0];
            String[] JsonResponse = null;
            URL url = Network.getURL(bob);

            try {
                String JsonObject = Network.getResponse(url);
                JsonResponse = JsonParse.parsedData(FullForecast.this, JsonObject);
                return JsonResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] JsonResponse) {
            mPBFull.setVisibility(View.INVISIBLE);
//            mCity.setText(JsonResponse);
            if(JsonResponse != null){
                mAdapter.setWeatherData(JsonResponse);
            } else{

            }
        }
    }


}
