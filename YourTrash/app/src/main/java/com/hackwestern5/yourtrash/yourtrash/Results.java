package com.hackwestern5.yourtrash.yourtrash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        // Get the Intent that started this activity and extract information
        Intent intent = getIntent();

        String object = intent.getStringExtra(StartUp.OBJECT);
        TextView objectBox = findViewById(R.id.databox_object);
        objectBox.setText(object);

        String location = intent.getStringExtra(StartUp.LOCATION);
        TextView locationBox = findViewById(R.id.databox_location);
        locationBox.setText(location);
    }
}
