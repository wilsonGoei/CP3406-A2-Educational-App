package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView displayScore;
    private String score;

    private Database db;

    public void playAgain(View view){
        Intent goToPlay = new Intent(GameOverActivity.this, game_activity.class);
        startActivity(goToPlay);
        db.addScore(Integer.parseInt(score));
        db.close();
    }

    public void mainMenu(View view){
        Intent goToMainMenu = new Intent(GameOverActivity.this, MainActivity.class);
        startActivity(goToMainMenu);
        db.addScore(Integer.parseInt(score));
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score = getIntent().getExtras().get("score").toString();

        displayScore = (TextView)findViewById(R.id.scoreText);

        displayScore.setText("Score: "+ score);

        db = new Database(this);
    }
}
