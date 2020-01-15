package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Leaderboards extends AppCompatActivity {

    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        db = new Database(this);

        ArrayList <Integer> anArray;
        ArrayList <String > ranks = new ArrayList<>();
        anArray = db.getAllScores();

        Collections.sort(anArray, Collections.reverseOrder());

        int counter = 1;
        if (anArray.size()<10){
            for (int score:anArray){
                ranks.add(String.format(Locale.getDefault(),"%1$2s. %2$3s ",Integer.toString(counter),Integer.toString(score)));
                counter++;
            }
        }else{
            for (int x=0; x<10; x++){
                ranks.add(String.format(Locale.getDefault(), "%1$2s. %2$3s ", Integer.toString(counter), Integer.toString(anArray.get(x))));
                counter++;
            }
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.high_scores_view,ranks);
        ListView listView = (ListView)findViewById(R.id.highScoresList);
        listView.setAdapter(myAdapter);
        listView.setDivider(null);

        MediaPlayer backgroundMusic = MediaPlayer.create(Leaderboards.this,R.raw.background_music);
        backgroundMusic.start();
    }
}
