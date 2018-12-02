package com.example.david.better_david_joe;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class BetterLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        ConstraintLayout launchLayout = findViewById(R.id.launchScreen);
        launchLayout.setOnClickListener(new OnClickListener() {

            // When the screen is clicked, it will initialize the app home screen
            @Override
            public void onClick(View v) {
                Intent appBrowser = new Intent(BetterLauncher.this, BetterApp.class);
                startActivity(appBrowser);

            }

        });

    }



}
