package com.example.david.better_david_joe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class BetterOracle extends AppCompatActivity {

    private static final String TAG = "CLOCKLOG";
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_screen);

        Calendar currentTime = Calendar.getInstance();

        TextView dayNight = findViewById(R.id.dayNight);
        ImageView backdrop = findViewById(R.id.backgroundColor);

        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int month = currentTime.get(Calendar.MONTH);

        boolean isDay = false;

        if (month >= 11 || month == 1) {
            if (hour >= 9 && hour < 17) {
                isDay = true;
            }
        } else if (month == 2) {
            if (hour >= 8 && hour < 18) {
                isDay = true;
            }
        } else if (month >= 5 && month < 8) {
            if (hour >= 6 && hour < 22) {
                isDay = true;
            }
        } else {
            if (hour >= 7 && hour < 20) {
                isDay = true;
            }
        }

        String dayMessage = "@string/geoffChallen";

        if (isDay) {
            dayMessage = "It is currently Daytime.";
            backdrop.setImageResource(R.drawable.sun_background);
        } else {
            dayMessage = "It's Bed O' Clock, you best be sleepin'.";
            backdrop.setImageResource(R.drawable.moon_background);
            //set Text Color as well
        }


        dayNight.setText(dayMessage);





    }
}