package com.example.david.better_david_joe;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BetterClock extends AppCompatActivity {

    private static final String TAG = "CLOCKLOG";

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

        if (month >= 10 || month == 0) {
            if (hour >= 9 && hour < 17) {
                isDay = true;
            }
        } else if (month == 1) {
            if (hour >= 8 && hour < 18) {
                isDay = true;
            }
        } else if (month >= 4 && month < 7) {
            if (hour >= 6 && hour < 22) {
                isDay = true;
            }
        } else {
            if (hour >= 7 && hour < 20) {
                isDay = true;
            }
        }

        String dayMessage;

        if (isDay) {
            dayMessage = "It is currently Day.";
            backdrop.setImageResource(R.drawable.sun_background);
            dayNight.setTextColor(getResources().getColor(R.color.dayColor));
        } else {
            dayMessage = "It is currently Night.";
            backdrop.setImageResource(R.drawable.moon_background);
            dayNight.setTextColor(getResources().getColor(R.color.nightColor));
        }
        dayNight.setText(dayMessage);
        Log.d(TAG, "Time Set");




    }
}