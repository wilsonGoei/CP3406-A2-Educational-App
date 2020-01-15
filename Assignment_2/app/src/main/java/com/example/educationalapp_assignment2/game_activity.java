package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class game_activity extends AppCompatActivity {

    private FishView gameView;
    private Handler handler = new Handler();
    private long Interval = 40;

    private SharedPreferences sharedPreferences;

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

        Timer timer = new Timer();
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
