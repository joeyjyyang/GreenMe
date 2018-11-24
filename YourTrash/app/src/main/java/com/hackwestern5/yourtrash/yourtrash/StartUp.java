package com.hackwestern5.yourtrash.yourtrash;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class StartUp extends AppCompatActivity {
//    final static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA,Manifest.permission.INTERNET};
//    LocationManager locationManager;
    public static final String LOCATION = "com.hackwestern5.yourtrash.yourtrash.location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
//        if (!isLocationEnabled())
//            showLocationAlert(1);
//
//        //note: add more permissions into PERMISSIONS and create checks for them all
//        if (Build.VERSION.SDK_INT >= 23 && !isLocationGranted()) {
//            requestPermissions(PERMISSIONS, PERMISSION_ALL);
//        } else requestLocation();
    }
    //Update textbox with current location
    private void updateLocationBox(String location)
    {
        EditText editText = (EditText)findViewById(R.id.Test_Location);
        editText.setText(location);
    }

    public void locationAction(View view)
    {
        //Find location using location thing then update
        updateLocationBox("It Works!");
    }

    public void openResults(View view)
    {
        Intent intent = new Intent(this, Results.class);
        intent.putExtra(LOCATION,"Toronto");
        startActivity(intent);
    }

//    private void showLocationAlert(final int status) {
//        String message, title, btnText;
//        if (status == 1) {
//            message = "To continue, turn on device location.";
//            title = "Enable Location";
//            btnText = "Location Settings";
//        } else {
//            message = "Please allow this app to access location!";
//            title = "Permission access";
//            btnText = "Grant";
//        }
//        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setCancelable(false);
//        dialog.setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        if (status == 1) {
//                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                            startActivity(myIntent);
//                        } else
//                            requestPermissions(PERMISSIONS, PERMISSION_ALL);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        finish();
//                    }
//                });
//        dialog.show();
//    }
//
//    private boolean isLocationEnabled() {
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//    }
//
//    //Permission Checks
//
//    private boolean isLocationGranted() {
//        if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED || checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            Log.v("mylog", "Location permission is granted");
//            return true;
//        } else {
//            Log.v("mylog", "Location permission not granted");
//            return false;
//        }
//    }
}
