package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
          @Override
            public void run(){
              try{
                  sleep(1000);
              } catch (Exception exeption){
                  exeption.printStackTrace();
              }
              finally {
                  Intent menuIntent = new Intent(Splash.this,MainActivity.class);
                  startActivity(menuIntent);
              }
          }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
