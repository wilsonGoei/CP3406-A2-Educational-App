package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView displayScore;
    private String score;

    public void playAgain(View view){
        Intent goToPlay = new Intent(GameOverActivity.this, game_activity.class);
        startActivity(goToPlay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score = getIntent().getExtras().get("score").toString();

        displayScore = (TextView)findViewById(R.id.scoreText);

        displayScore.setText("Score: "+ score);
    }
}
