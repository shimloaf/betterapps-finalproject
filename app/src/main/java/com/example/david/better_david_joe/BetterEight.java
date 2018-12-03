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
import android.view.View;
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
                (Sensor.TYPE_ACCELEROMETER), 1000000);

        acceleration = mSensorManager.GRAVITY_EARTH;
        prevAcceleration = mSensorManager.GRAVITY_EARTH;
        shakinVal = 0.00f;

        message = "Ask it question \nthen give it \na shake...";
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
                acceleration = (float) Math.sqrt((double) (x * x + y * y + z * z));
                float delta = acceleration - prevAcceleration;

                shakinVal = shakinVal * 0.9f + delta;


                if (shakinVal > 12 && canShake) {

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

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    canShake = true;

                                }

                            }, 3000);




                        }

                    }, 3000 + (1000 * Math.round(Math.random() * 2)));


            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void pick_option() {
        TextView result = findViewById(R.id.ballResult);

        int choice = 0;

        choice = (int) (Math.random()*40);
        message = "I don't know.";

        switch (choice) {
            case 0:
                message = "Dunno, try the \nmagic 9-ball.";
                break;
            case 1:
                message = "Ask again later, or\nbetter yet, don't.";
                break;
            case 2:
                message = "Ask an expert.";
                break;
            case 3:
                message = "I really have\nno idea.";
                break;
            case 4:
                message = "I don't want \nto answer that.";
                break;
            case 5:
                message = "Why do you think I \nwould know the \nanswer to \nthat?";
                break;
            case 6:
                message = "No clue, \nhonestly.";
                break;
            case 7:
                message = "Yes. No wait, actually, \nNo. What was the \nquestion again?";
                break;
            case 8:
                message = "Have you tried \nWikipedia?";
                break;
            case 9:
                message = "... No comment.";
                break;
            case 10:
                message = "I'm not gonna \nanswer that.";
                break;
            case 11:
                message = "I know the answer, I'm \njust not going to\ntell you.";
                break;
            case 12:
                message = "Do you honestly \nthink I care?";
                break;
            case 13:
                message = "Despite the name, \nI'm not really \nmagic, buddy.";
                break;
            case 14:
                message = "I... I don't wanna \ntalk about it \nright now.";
                break;
            case 15:
                message = "Signs point to Yes! \nWait, that wasn't \nfor you.";
                break;
            case 16:
                message = "I don't know, have \nyou tried posting \non the forum?";
                break;
            case 17:
                message = "I don't know, have \nyou tried going to \noffice hours?";
                break;
            case 18:
                message = "Signs point \nto maybe.";
                break;
            case 19:
                message = "Results hazy, \ndon't try again.";
                break;
            case 20:
                message = "Definitely maybe.";
                break;
            case 21:
                message = "Could be, \nI don't know.";
                break;
            case 22:
                message = "Look inside yourself, \nthe true answer \nis within.";
                break;
            case 23:
                message = "I think you know \nthe answer.";
                break;
            case 24:
                message = "La La La La La,\nI can't hear \nyou!";
                break;
            case 25:
                message = "What was that? Oh \nwait, I don't \ncare.";
                break;
            case 26:
                message = "Eh, I really don't \nfeel like being \nmagic right \nnow.";
                break;
            case 27:
                message = "W-where am I? Last \nthing I remember I\nwas sitting in the \ncorner pocket!";
                break;
            case 28:
                message = "Why the hell \nare you asking \nan 8-ball?";
                break;
            case 29:
                message = "Uhhhhh... Hang on I \nknow this one... \nUhhhh... Sixty\npoint Two?";
                break;
            case 30:
                message = "Let me answer \nyour question with \nanother question. \nWho cares?";
                break;
            case 31:
                message = "Come back in a \nyear, I'll have \nfigured it out \nby then.";
                break;
            case 32:
                message = "Help! I've been stuck \nin this 8-Ball for \n35 years!";
                break;
            case 33:
                message = "Don't know, \ndon't care.";
                break;
            case 34:
                message = "You're always asking \nme questions. Don't \nyou think I have \nquestions too?";
                break;
            case 35:
                message = "Gimme 20 bucks \nand try again.";
                break;
            case 36:
                message = "Signs point in \nall directions.";
                break;
        }

        result.setText(message);



    }
}