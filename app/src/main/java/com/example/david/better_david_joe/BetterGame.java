package com.example.david.better_david_joe;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BetterGame extends AppCompatActivity {

    private static final String TAG = "BOTLOG";
    private int points;
    private int betterPoints;
    private int wins;
    private int betterWins;
    private int victoryState = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        points = 0;
        betterPoints = 0;
        wins = 0;
        betterWins = 0;

        updateScore();

    }


    public void updateScore() {
        TextView pointsCounter = findViewById(R.id.totalPoints);
        TextView betterPointsCounter = findViewById(R.id.totalBpoints);
        TextView winsCounter = findViewById(R.id.totalWins);
        TextView betterWinsCounter = findViewById(R.id.totalBWins);

        String pointsText = "" + getResources().getText(R.string.pointsText) + " " +  points;
        String betterPointsText = "" + getResources().getText(R.string.betterPointsText) + " " + betterPoints;
        String winsText = "" + getResources().getText(R.string.winsText) + " " + wins;
        String betterWinsText = "" + getResources().getText(R.string.betterWinsText) + " " + betterWins;

        pointsCounter.setText(pointsText);
        betterPointsCounter.setText(betterPointsText);
        winsCounter.setText(winsText);
        betterWinsCounter.setText(betterWinsText);

    }

    public void updateButtons(int wonGame) {

        Button goalButton = findViewById(R.id.betterGoalButton);
        Button gpButton = findViewById(R.id.bGPButton);
        Button obButton = findViewById(R.id.bOButton);

        if (betterPoints == 10) {
            goalButton.setBackground(ContextCompat.getDrawable(BetterGame.this, R.drawable.reallygoldroundedcorners));
        } else {
            goalButton.setBackground(ContextCompat.getDrawable(BetterGame.this, R.drawable.reallygrayroundedcorners));
        }

        if (wonGame == -1){
            String youLost = "You lost.";
            gpButton.setText(youLost);
            gpButton.setBackground(ContextCompat.getDrawable(BetterGame.this, R.drawable.reallygrayroundedcorners));
            obButton.setVisibility(View.GONE);
            goalButton.setVisibility(View.GONE);
        } else if (wonGame == 1) {
            String youWon = "You Won!";
            gpButton.setText(youWon);
        } else if (wonGame == 2){
            gpButton.setBackground(ContextCompat.getDrawable(BetterGame.this, R.drawable.reallygreenroundedcorners));
            String youBetterWon = "You Won\n the Better Game!";
            gpButton.setText(youBetterWon);
        } else if (wonGame == 3) {
            gpButton.setBackground(ContextCompat.getDrawable(BetterGame.this, R.drawable.reallygoldroundedcorners));
            String youBetterWon = "Even Better Goal!!!";
            obButton.setVisibility(View.GONE);
            goalButton.setVisibility(View.GONE);
            gpButton.setText(youBetterWon);
        } else {
            gpButton.setText(getResources().getString(R.string.betterGameplay));
        }


    }




    public void rulesClick(View view) {

        AlertDialog.Builder bob = new AlertDialog.Builder(BetterGame.this);
        bob.setTitle(getResources().getString(R.string.betterGameTitle));

        TextView myMsg = new TextView(this);
        myMsg.setText(getResources().getString(R.string.rulesText));
        myMsg.setGravity(Gravity.START);
        myMsg.setPadding(30, 0, 30, 0);
        myMsg.setVerticalScrollBarEnabled(true);
        myMsg.setMovementMethod(new ScrollingMovementMethod());
        bob.setView(myMsg);
        bob.setCancelable(true);
        AlertDialog rulesText = bob.create();
        rulesText.show();
    }


    public void increasePoints(View view) {

        int wonGame = 0;

        if (victoryState == 0) {

            points++;

            if (points == 11) {
                points = 0;
                betterPoints++;
            }

            if (betterPoints == 10) {
                victoryState = 1;
            }


        }

        if (victoryState == -1) {
            wonGame = -1;
        }

        if (victoryState == 3) {
            betterWins++;
            wonGame = 2;
            updateScore();
            victoryState = 2;
            endGame(victoryState);
        }

        if (victoryState == 2) {
            wonGame = 2;
        }

        updateButtons(wonGame);
        updateScore();
    }

    public void decreasePoints(View view) {

        if (victoryState == 0) {

            if (points == 0 && betterPoints > 0) {
                betterPoints--;
            } else {
                points--;
            }

            if (points == -5) {
                victoryState = -1;
                endGame(victoryState);
                int wonGame = -1;
                updateButtons(wonGame);
            }

        }
        updateScore();
    }

    public void increaseWins(View view) {
        if (victoryState == 1) {
            int wonGame = 1;
            betterPoints = 0;
            wins++;
            victoryState = 0;
            if (wins == 10) {
                wonGame = 3;
                victoryState = 3;
            }
            updateScore();
            updateButtons(wonGame);
        }
    }

    public void endGame(int victoryState) {

        if (victoryState == 2) {
            Log.d(TAG, "game won");
        } if (victoryState == -1) {
            Log.d(TAG, "game lost");
        }

    }



}