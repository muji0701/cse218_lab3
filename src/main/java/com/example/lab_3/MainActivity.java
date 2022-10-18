package com.example.lab_3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.example.lab_3.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;
    LocationManager lm;
    double currentLon = 0;
    double currentLat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.err.println("------------- start --------------");

        System.err.println("------------- created lm --------------");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            System.err.println("------------- permission error --------------");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            System.out.println("------- lm is enabled -------");
        }

        if (lm.isLocationEnabled()) {
            System.out.println("------- lm is location enabled -------");
        }

        System.out.println(lm.getAllProviders());

        LocationListener Loclist = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                System.err.println("------- on location changed -------- ");
                //Get new location

                //get the current lat and long
                currentLat = location.getLatitude();
                currentLon = location.getLongitude();
                System.out.println("getLatitude");

//            mTextView.setText(String.format("Lat %.2f", currentLat));

            }
        };

        System.err.println("------- request location update ----------");
        try {
            lm.requestLocationUpdates(lm.GPS_PROVIDER, 1000, 0, Loclist);
        } catch (Exception ex) {
            System.out.println("------------");
            System.out.println(ex);
        }

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        //mTextView = binding.text;
//        mTextView.setText("dew");
    }


}