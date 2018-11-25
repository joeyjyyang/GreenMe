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

        String final_res = returnExplanation(location, object_name);
        TextView resultsBox = findViewById(R.id.databox_results);
        resultsBox.setText(final_res);
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

            if (obj.equals("material") || obj.equals("product") || obj.equals("wool") || obj.equals("hardware")) {
                objbuff = tempArray[index_2].split(delimiter);
                obj = objbuff[0].trim();
            }

            if (obj.equals("water") || obj.equals("bottle")) {
                return "plastic bottle";
            }

            if (obj.equals("cylinder")){
                return "aluminum can";
            }

            return obj;
        }
        return out;
    }

    private String returnExplanation(String location, String object){
        String results = "";
        if (location.equals("London")) {
            if (object.equals("plastic bottle") || object.equals("plastic")) {
                results = "London: Please place all your plastic containers in your Containers Bin.";
            } else if (object.equals("paper towel") || object.equals("paper")) {
                results = "London: Please dispose of any paper towels and napkins in your Garbage Bin.";
            } else if (object.equals("aluminum can")) {
                results = "London: Please place this item in your Blue Bin.  If appropriate, please empty and rinse this item.";
            } else if (object.equals("electronic device") || object.equals("laptop") || object.equals("technology") || object.equals("wire")) {
                results = "London: Please dispose all electronics at your local City of London Drop-off Depot.";
            } else if (object.equals("fruit") || object.equals("banana")){
                results = "London: Please place this item in your Green Bin.";
            } else {
                results = "The material looks like garbage please dispose of it appropriately.";
            }
        }
        else if (location.equals("Toronto")) {
            if (object.equals("plastic bottle")) {
                results = "Toronto: Please place empty containers in your Blue Bin, leave lids and sprayers on.";
            }
            else if (object.equals("paper towel") || object.equals("paper")) {
                results = "Toronto: Please place this item in your Green Bin.";
            }
            else if (object.equals("aluminum can")) {
                results = "Toronto: Please place this item in your Blue Bin.  If appropriate, please empty and rinse this item.";
            }
            else if (object.equals("electronic device") || object.equals("laptop") || object.equals("technology") || object.equals("wire") ||
                    object.equals("netbook") || object.equals("audio equipment") || object.equals("headphones")){
                results = "Toronto: Please dispose all electronics  at your local City of Toronto Drop-off Depot";
            }
            else if (object.equals("fruit") || object.equals("banana")) {
                results = "Toronto: Please place this item in your Green Bin.";
            } else {
                results = "The material looks like garbage please dispose of it appropriately.";
            }
        }
        else {
            results = "Sorry, London and Toronto are currenty the only supported cities.";
        }
        return results;
    }
}
