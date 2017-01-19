package me.andrewhanselee.cloudydays;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgBar;
    private RecyclerView mRView;
    private Adapter mAdapter;
    private TextView mError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgBar = (ProgressBar) findViewById(R.id.pb_load);
        mRView = (RecyclerView) findViewById(R.id.rv_forecast);
        mError = (TextView) findViewById(R.id.tv_error);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRView.setLayoutManager(layoutManager);

        mAdapter = new Adapter();
        mRView.setAdapter(mAdapter);

        loadWeatherData();
    }

    String location = "Montreal,ca";
    String mode = "json";
    String units = "metric";
    String numberDays = "1";


    private void loadWeatherData() {
        URL searchURL = JsonParse.getWeather(location, mode, units, numberDays);
        new retrieveData().execute(searchURL);
        mRView.setVisibility(View.VISIBLE);
        mError.setVisibility(View.INVISIBLE);
    }

    private void showError(){
        mError.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public class retrieveData extends AsyncTask <URL, Void, String[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // loading bar
            mProgBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(URL... params) {
            URL search = params[0];
            String weatherResponse = null;

            try {
                weatherResponse = JsonParse.getResponse(search);

                String[] responseArray = OpenWeatherJson.weatherJsonData(MainActivity.this, weatherResponse);

                return responseArray;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            if(strings != null){
                mRView.setVisibility(View.VISIBLE);
                mAdapter.setData(strings);
            } else{
                showError();
            }
            //loading bar gone
            mProgBar.setVisibility(View.INVISIBLE);
        }
    }


}
