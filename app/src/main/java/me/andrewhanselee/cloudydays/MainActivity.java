package me.andrewhanselee.cloudydays;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;

public class MainActivity extends AppCompatActivity
//        implements AdapterFullForecast.AdapterFullForecastOnClick,
//        LoaderManager.LoaderCallbacks<String[]>
{

    private ProgressBar mProgBar;
    private RecyclerView mRView;
    private TextView mError;
    private TextView mCity;
//    private TextView CurrentTemp;
    private TextView CityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar bar = getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable("COLOR"));

        mProgBar = (ProgressBar) findViewById(R.id.pb_load);
//        mRView = (RecyclerView) findViewById(R.id.rv_forecast);
        mError = (TextView) findViewById(R.id.tv_error);
        mCity = (TextView) findViewById(R.id.tv_city);
//        CurrentTemp = (TextView) findViewById(R.id.tv_temp);
        CityName = (TextView) findViewById(R.id.city_name);
        showWeather();
    }



    public static String city = "Montreal,ca";
    public void showWeather() {
        URL url = Network.getURL(city);
        new getData().execute(url);
    }

    public class getData extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL weather = params[0];
            String JsonResponse = null;

            try {
                String JsonObject = Network.getResponse(weather);
                JsonResponse = JsonParse.parsedDay(MainActivity.this, JsonObject);
                return JsonResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            mProgBar.setVisibility(View.INVISIBLE);
            mCity.setText(JsonResponse);
            CityName.setText(city);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    int requestcode = 5;
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivityForResult(intent, requestcode);
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == requestcode) {
            showWeather();
        }
    }

    public void fullForecast(View view){
        Intent intent = new Intent(this, FullForecast.class);
        startActivity(intent);
    }


}
