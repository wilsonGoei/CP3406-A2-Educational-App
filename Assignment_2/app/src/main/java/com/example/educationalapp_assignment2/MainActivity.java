package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView shareButton;

    private MediaPlayer backGroundMusic;

    private String songsBoolean;

    public void playScreen(View view){
        Intent toPlayScreen = new Intent(MainActivity.this, game_activity.class);
        startActivity(toPlayScreen);
    }

    public void leaderBoardsScreen(View view){
        Intent toLeaderBoards = new Intent(MainActivity.this, Leaderboards.class);
        startActivity(toLeaderBoards);


    }

    public void settingsScreen(View view){
        Intent toSettings = new Intent(MainActivity.this, Settings.class);
        startActivity(toSettings);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backGroundMusic = MediaPlayer.create(MainActivity.this,R.raw.background_music);
        backGroundMusic.start();

        shareButton = (ImageView)findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Let's play Fishy Math, make math more fun!!";
                String shareSub = "Invitation";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("songsBoolean",MODE_PRIVATE);
        songsBoolean = sharedPreferences.getString("songsBoolean","true");
        if (songsBoolean.equalsIgnoreCase("true")){
            backGroundMusic.start();
            Log.i("statusStartOn",songsBoolean);
        }else if (songsBoolean.equalsIgnoreCase("false")){
            backGroundMusic.pause();
            Log.i("statusStartOff",songsBoolean);
        }
    }
}
