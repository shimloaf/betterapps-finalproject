package com.example.david.better_david_joe;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ImageView;

public class BetterApp extends AppCompatActivity {

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
        Intent appBrowser = new Intent(BetterApp.this, BetterWeather.class);
        startActivity(appBrowser);
    }

    public void openCalc(View view) {
        Intent appBrowser = new Intent(BetterApp.this, BetterCalc.class);
        startActivity(appBrowser);
    }

    public void openBot(View view) {
        Intent appBrowser = new Intent(BetterApp.this, BetterBot.class);
        startActivity(appBrowser);
    }


}
