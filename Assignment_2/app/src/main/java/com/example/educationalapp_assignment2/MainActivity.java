package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button playButton;
    public Button leaderboards;
    public Button Option;

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

        MediaPlayer backGroundMusic = MediaPlayer.create(MainActivity.this,R.raw.background_music);
        backGroundMusic.start();
    }


}
