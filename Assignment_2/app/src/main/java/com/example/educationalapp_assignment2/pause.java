package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.RelativeLayout;


public class pause extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        RelativeLayout pauseLayout = (RelativeLayout)findViewById(R.id.pauseBackground);

        pauseLayout.getBackground().setAlpha(150);

    }

    public void exit(View view){
        Intent main_menu = new Intent(pause.this, MainActivity.class);
        startActivity(main_menu);
    }

    public void continueAgain(View view){
        Intent toGame = new Intent(pause.this,game_activity.class);
        startActivity(toGame);
        finish();
    }
}
