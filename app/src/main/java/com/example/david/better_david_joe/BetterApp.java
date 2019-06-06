package com.example.david.better_david_joe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BetterApp extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String TAG = "MAIN_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

    }


    public void openClock(View view) {
        Intent appBrowser = new Intent(BetterApp.this, BetterClock.class);
        startActivity(appBrowser);
    }

    public void openEight(View view) {
        Intent appBrowser = new Intent(BetterApp.this, BetterEight.class);
        startActivity(appBrowser);
    }

    public void openOracle(View view) {
        Intent appBrowser = new Intent(BetterApp.this, BetterOracle.class);
        startActivity(appBrowser);
    }

    public void openWeather(View view) {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

                Intent appBrowser = new Intent(BetterApp.this, BetterWeather.class);
                startActivity(appBrowser);

        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    22);

            Log.d(TAG,"" + ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION));
        }


    }

    public void openCalc(View view) {
        Intent appBrowser = new Intent(BetterApp.this, BetterCalc.class);
        startActivity(appBrowser);
    }

    public void openGame(View view) {
        Intent appBrowser = new Intent(BetterApp.this, BetterGame.class);
        startActivity(appBrowser);
    }


}
