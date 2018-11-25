package com.hackwestern5.yourtrash.yourtrash;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


public class StartUp extends AppCompatActivity {
    final static int PERMISSION_ALL = 1;
    public static final String OBJECT = "com.hackwestern5.yourtrash.yourtrash.object";
    public static final String LOCATION = "com.hackwestern5.yourtrash.yourtrash.location";

    final static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionGranted()) {
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        } else requestLocation();
        if (!isLocationEnabled())
            showAlert(1);
    }

    private void showAlert(final int status) {
        String message, title, btnText;
        if (status == 1) {
            message = "Enable Location";
            title = "Enable Location";
            btnText = "Location Settings";
        } else {
            message = "Needs access to location!";
            title = "Permission access";
            btnText = "Grant";
        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        if (status == 1) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        } else
                            requestPermissions(PERMISSIONS, PERMISSION_ALL);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }

    public void onLocationChanged(Location location) {
        Location myCoordinates = new Location(locationManager.NETWORK_PROVIDER);
    }

    private void requestLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.getLastKnownLocation(provider);
        //locationManager.requestLocationUpdates(provider, 10000, 10, this);
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean isPermissionGranted() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    //Get location from GPS and update box
    private void updateLocationBox(String location) {
        EditText editText = (EditText) findViewById(R.id.Test_Location);
        editText.setText(location);
    }

    public void locationAction(View view) throws IOException {
        Location location = null;
        double lat = 0, lng = 0;
        int locManagerNull = 1;
        int locationNull = 1;
        if (locationManager != null) {
//            locManagerNull = 0;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                Geocoder geoCoder = new Geocoder(getApplicationContext());
                List<Address> list = geoCoder.getFromLocation(location
                        .getLatitude(), location.getLongitude(), 1);
                if (list != null & list.size() > 0) {
                    Address address = list.get(0);
                    String finalAddress = address.getLocality();
                    if(finalAddress.equals("London")||finalAddress.equals("Toronto")){
                        updateLocationBox(finalAddress);
                    }
                    else
                    {
//                        updateLocationBox("Enter a supported Municipality");
                        Toast toast = Toast.makeText(getApplicationContext(), "Municipality not supported!", Toast.LENGTH_LONG);
                        toast.show();
                        updateLocationBox("");
                    }

                }
            }
        }

    }

        //Sends to Results page
        public void openCamera (View view){
            EditText editText = (EditText) findViewById(R.id.Test_Location);
             String finalAddress = editText.getText().toString();
            if(finalAddress.equals("London")||finalAddress.equals("Toronto")){
                Intent intent = new Intent(this, CameraMain.class);
                EditText currentLocation = (EditText) findViewById(R.id.Test_Location);
                String location = currentLocation.getText().toString();
                String object = "Object";
                intent.putExtra(OBJECT, object);
                intent.putExtra(LOCATION, location);
                startActivity(intent);
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Municipality not supported!", Toast.LENGTH_LONG);
                toast.show();
                updateLocationBox("");
            }


        }
    }

