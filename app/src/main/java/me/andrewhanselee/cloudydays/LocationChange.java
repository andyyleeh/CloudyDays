package me.andrewhanselee.cloudydays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class LocationChange extends AppCompatActivity {

    Button change;
    MultiAutoCompleteTextView newloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_set);

        change = (Button) findViewById(R.id.location_button);
        newloc = (MultiAutoCompleteTextView) findViewById(R.id.newLocation);
    }

    public void changeLocation(View view){
        MainActivity.city = newloc.getText().toString();

    }
}
