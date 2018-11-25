package com.hackwestern5.yourtrash.yourtrash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.String;

public class Results extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the Intent that started this activity and extract information
        Intent intent = getIntent();

        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);

        String object = intent.getStringExtra(StartUp.OBJECT);
        String object_name = returnObjectName(object);

        TextView objectBox = findViewById(R.id.databox_object);
        objectBox.setText(object_name);

        String location = intent.getStringExtra(StartUp.LOCATION);
        TextView locationBox = findViewById(R.id.databox_location);
        locationBox.setText(location);
    }

    private String returnObjectName(String object){
        String out = "uknown object";
        String[] tempArray;
        String delimiter = "\n";
        tempArray = object.split(delimiter);
        int index = 0;
        int index_2 = 0;
        double l_val_1 = 0.00;
        double l_val_2 = 0.00;
        if(tempArray.length > 1) {
            for (int i = 0; i < tempArray.length; i++) {
                double f = Float.valueOf(tempArray[i].replaceAll("[^\\d.]+|\\.(?!\\d)", ""));
                if (f > l_val_1) {
                    if (l_val_2 > 0) {
                        index_2 = index;
                        l_val_2 = l_val_1;
                    }
                    index = i;
                    l_val_1 = f;
                } else {
                    if (f > l_val_2) {
                        index_2 = i;
                        l_val_2 = f;
                    }
                }
            }
            delimiter = "0";
            String[] objbuff = tempArray[index].split(delimiter);
            String obj = objbuff[0].trim();

            if (obj.equals("material") || obj.equals("product") || obj.equals("wool")) {
                objbuff = tempArray[index_2].split(delimiter);
                obj = objbuff[0].trim();
            }

            if (obj.equals("water")) {
                return "water bottle";
            }
            return obj;
        }
        return out;
    }
}
