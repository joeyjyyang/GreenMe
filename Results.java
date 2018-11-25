package com.hackwestern5.yourtrash.yourtrash;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;

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

        String results = "";
        if (object.equals("plastic bottle")) {
            results = "Please place empty containers in your Blue Bin, leave lids and sprayers on. All recycling should fit in your Blue Bin with the lid closed. If you have extra recycling, please keep materials until your next Blue Bin collection. Occasionally you can place extra recycling in clear plastic bags beside the full blue bin. If you wish to exchange your Blue Bin for a larger size (at no cost), please call 311.";
        }
        else if (object.equals("paper towel")) {
            results = "Please place this item in your Green Bin.  Line either your indoor container or outdoor Green Bin with a plastic bag or kraft paper bag.  Do not line both.  Green Bins can not weigh more than 20 kgs/44 lbs.  Excess organic material can be set out in clear plastic bags (weighing less than 20 kgs/44 lbs) beside the Green Bin.  You can get an additional green bin at <a href=\"http://www.toronto.ca/environment_days/index.htm\" target=\"_blank\">Community Environment Days</a> , at one of the City's <a href=\"http://www.toronto.ca/garbage/depots.htm\" target=\"_blank\">Recycling and Solid Waste Depots</a> or by calling 311.";
        }
        else if (object.equals("aluminum can")) {
            results = "Please place this item in your Blue Bin.  If appropriate, please empty and rinse this item.  All recycling should fit in your Blue Bin with the lid closed.  If you have extra recycling, please keep materials until your next Blue Bin collection.   Occasionally you can place extra recycling in clear plastic bags beside the full blue bin. If you wish to exchange your Blue Bin for a larger size (at no cost), please call 311.";
        }
        TextView resultsBox = findViewById(R.id.databox_results);
        resultsBox.setText(results);
            /*Scanner scanner = null;
        try {
            scanner = new Scanner(new File("../../assets/test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = new ArrayList<>();
        while(scanner.hasNextLine()) {
            //list.add(scanner.nextLine().split(",")[1]);
            list.add(scanner.nextLine());
        }
        scanner.close();

        for(int i = 0; i < list.size(); i++) {
            String[] data = list.get(i).split(",");
            String item_name = data[0];
            String alt_names = data[1];
            String item_ID = data[2];
            String results = data[3];
            if (item_name == object) {
                TextView messageBox = findViewById(R.id.databox_results);
                messageBox.setText(results);
            }
        }
        */
    }
}
