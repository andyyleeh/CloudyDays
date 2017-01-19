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
    private TextView mError;
    private TextView mCity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgBar = (ProgressBar) findViewById(R.id.pb_load);
        mRView = (RecyclerView) findViewById(R.id.rv_forecast);
        mError = (TextView) findViewById(R.id.tv_error);
        mCity = (TextView) findViewById(R.id.tv_city);

        showWeather();
    }

    String city = "montreal,ca";

    public void showWeather(){
        URL url = Network.getURL(city);
       new getData().execute(url);
    }

    public class getData extends AsyncTask<URL, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL weather = params[0];
            String JsonResponse = null;

            try{
                JsonResponse = Network.getResponse(weather);
            }catch(Exception e){
                e.printStackTrace();
            }
            return JsonResponse;
        }

        @Override
        protected void onPostExecute(String strings) {
            mProgBar.setVisibility(View.INVISIBLE);
            mCity.setText(strings);
        }
    }






}
