package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


import java.util.Timer;
import java.util.TimerTask;

public class game_activity extends AppCompatActivity {

    public FishView gameView;
    public Handler handler = new Handler();
    private long Interval = 40;

    private SharedPreferences sharedPreferences;

    public Timer timer;

    public boolean pause_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new FishView(this);
        setContentView(gameView);

        sharedPreferences = getApplicationContext().getSharedPreferences("difficulties", Context.MODE_PRIVATE);
        String difficulty = sharedPreferences.getString("difficulty","easy");
        if (difficulty.equalsIgnoreCase("medium"))
            Interval = 30;
        else if (difficulty.equalsIgnoreCase("hard")){
            Interval = 20;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);

    }

    @Override
    public void onBackPressed() {
        paused();
        Intent pauseActivity = new Intent(this, pause.class);
        startActivity(pauseActivity);
    }

    public void paused(){

        if (pause_flg == false){
            pause_flg = true;

            timer.cancel();
            timer = null;
        } else {
            pause_flg = true;

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            gameView.invalidate();
                        }
                    });
                }
            }, 0, Interval);
        }

    }

}
