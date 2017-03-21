package me.andrewhanselee.cloudydays;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class Settings extends AppCompatActivity {

    private Button theme;
    private Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        theme = (Button) findViewById(R.id.tv_themeSet);
        location = (Button) findViewById(R.id.tv_locationSet);

    }


    public void locationChange(View view){
        Intent intent = new Intent(this, LocationChange.class);
        startActivity(intent);
    }
}
