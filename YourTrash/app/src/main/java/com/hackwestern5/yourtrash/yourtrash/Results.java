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


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(StartUp.LOCATION);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.databox_location);
        textView.setText(message);
    }
}
