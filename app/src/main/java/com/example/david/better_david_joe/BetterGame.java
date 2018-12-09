package com.example.david.better_david_joe;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class BetterGame extends AppCompatActivity {

    private static final String TAG = "BOTLOG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);



    }

    public void rulesClick(View view) {

        AlertDialog.Builder bob = new AlertDialog.Builder(BetterGame.this);
        bob.setTitle(getResources().getString(R.string.betterGameTitle));

        TextView myMsg = new TextView(this);
        myMsg.setText(getResources().getString(R.string.rulesText));
        myMsg.setGravity(Gravity.LEFT);
        myMsg.setPadding(30,0,30,0);
        myMsg.setVerticalScrollBarEnabled(true);
        myMsg.setMovementMethod(new ScrollingMovementMethod());
        bob.setView(myMsg);
        bob.setCancelable(true);
        AlertDialog rulesText = bob.create();
        rulesText.show();
    }



}