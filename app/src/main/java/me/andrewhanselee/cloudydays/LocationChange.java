package me.andrewhanselee.cloudydays;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class LocationChange extends AppCompatActivity {

    Button change;
    MultiAutoCompleteTextView newloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_set);

        change = (Button) findViewById(R.id.location_button);
        newloc = (MultiAutoCompleteTextView) findViewById(R.id.newLocation);
        newloc.setHint(MainActivity.city);




    }

    public void changeLocation(View view){
        Context context = getApplicationContext();
        CharSequence text = "Location changed.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        MainActivity.city = newloc.getText().toString();
        toast.show();
    }
}
