package com.example.david.better_david_joe;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.view.View;
import android.view.View.OnClickListener;

public class BetterEight extends AppCompatActivity{

    private static final String TAG = "EIGHTLOG";
    private Button mButton;
    private String message;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float acceleration;
    private float prevAcceleration;
    private float shakinVal;
    private boolean canShake = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_screen);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(sensorListener, mSensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acceleration = mSensorManager.GRAVITY_EARTH;
        prevAcceleration = mSensorManager.GRAVITY_EARTH;
        shakinVal = 0.00f;

        message = "Ask it question then give it a shake...";
        TextView result = findViewById(R.id.ballResult);
        result.setText(message);


    }

    private final SensorEventListener sensorListener = new SensorEventListener() {


        @Override
        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            prevAcceleration = acceleration;
            acceleration = (float) Math.sqrt((double) (x*x + y*y+ z*z));
            float delta = acceleration - prevAcceleration;

            shakinVal = shakinVal * 0.9f + delta;

            if (shakinVal > 35 && canShake) {

                canShake = false;
                //shaking detected, fade out, wait a random time, fade in

                TextView result = findViewById(R.id.ballResult);
                result.setVisibility(View.INVISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pick_option();
                        TextView result = findViewById(R.id.ballResult);
                        result.setVisibility(View.VISIBLE);
                        canShake = true;
                    }
                }, 2000 + (1000 * Math.round(Math.random()*3)));


            }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void fadeText(boolean b) {

        if (b) {

        } else {


        }



    }

    public void pick_option() {
        TextView result = findViewById(R.id.ballResult);

        int choice = 0;

        choice = (int) Math.floor(Math.random()*34);

        switch (choice) {
            case 0:
                message = "I don't know.";
                break;
            case 1:
                message = "Ask again later, or better yet, don't.";
                break;
            case 2:
                message = "Ask an expert.";
                break;
            case 3:
                message = "I really have no idea.";
                break;
            case 4:
                message = "I don't want to answer that.";
                break;
            case 5:
                message = "Why do you think I would know the answer to that?";
                break;
            case 6:
                message = "No clue, honestly.";
                break;
            case 7:
                message = "Yes. No wait, actually, No. What was the question again?";
                break;
            case 8:
                message = "Have you tried Wikipedia?";
                break;
            case 9:
                message = "... No comment.";
                break;
            case 10:
                message = "I'm not gonna answer that.";
                break;
            case 11:
                message = "I know the answer, I'm just not going to tell you.";
                break;
            case 12:
                message = "Do you honestly think I care?";
                break;
            case 13:
                message = "Despite the name, I'm not really magic, pal.";
                break;
            case 14:
                message = "I... I don't wanna talk about it right now.";
                break;
            case 15:
                message = "Signs point to Yes! Oh, hang on a minute, that was for the last guy.";
                break;
            case 16:
                message = "I don't know, have you tried posting on the forum?";
                break;
            case 17:
                message = "I don't know, have you tried going to office hours?";
                break;
            case 18:
                message = "Signs point to maybe.";
                break;
            case 19:
                message = "Results hazy, don't try again.";
                break;
            case 20:
                message = "Definitely maybe.";
                break;
            case 21:
                message = "Could be, I don't know.";
                break;
            case 22:
                message = "Look inside yourself, the true answer is within.";
                break;
            case 23:
                message = "I think you know the answer.";
                break;
            case 24:
                message = "Lalalala, I can't hear you!";
                break;
            case 25:
                message = "What was that? Oh wait, I don't care.";
                break;
            case 26:
                message = "Eh, I really don't feel like being magic right now.";
                break;
            case 27:
                message = "W-where am I? Last thing I remember I was sitting in the corner pocket!";
                break;
            case 28:
                message = "Why the hell are you asking an 8-ball?";
                break;
            case 29:
                message = "Uhhhhh... Hang on I know this one... Uhhhh... Sixty Four point Two?";
            case 30:
                message = "Let me answer your question with another question. Who cares?";
                break;
            case 31:
                message = "Come back in a year, I'll have figured it out by then";
                break;
            case 32:
                message = "Help! I've been trapped in this 8-Ball for 35 years!";
                break;
            case 33:
                message = "Don't know, don't care.";
                break;
            case 34:
                message = "You're always asking me questions. Don't you think I have questions too?";
                break;
        }

        result.setText(message);



    }
}