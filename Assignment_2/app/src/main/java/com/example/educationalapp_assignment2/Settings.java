package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner gameMode;

    private Switch musicSwitch;
    private Boolean musicStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        gameMode = (Spinner)findViewById(R.id.modeSpinner);

        gameMode.setOnItemSelectedListener(this);

        List<String> difficulties = new ArrayList<String>();
        difficulties.add("Easy");
        difficulties.add("Medium");
        difficulties.add("Hard");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, difficulties);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameMode.setAdapter(dataAdapter);

        musicSwitch = (Switch)findViewById(R.id.musicSwitch);
        musicSwitch.setChecked(true);
        musicStatus = musicSwitch.isChecked();
        SharedPreferences sharedPreferences = Settings.this.getSharedPreferences("songsBoolean",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("songsBoolean","true");
        editor.apply();
        Log.i("status",musicStatus.toString());
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    musicStatus = musicSwitch.isChecked();
                    SharedPreferences sharedPreferences = Settings.this.getSharedPreferences("songsBoolean",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("songsBoolean","true");
                    editor.apply();
                    Log.i("status",musicStatus.toString());
                }
                else {
                    musicStatus = musicSwitch.isChecked();
                    SharedPreferences sharedPreferences = Settings.this.getSharedPreferences("songsBoolean",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("songsBoolean","false");
                    editor.apply();
                    Log.i("status",musicStatus.toString());
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"Selected: " + item, Toast.LENGTH_LONG).show();

        if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("Easy")){
            SharedPreferences sharedPreferences = Settings.this.getSharedPreferences("difficulties",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("difficulty","easy");
            editor.apply();
        }
        else if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("Medium")){
            SharedPreferences sharedPreferences = Settings.this.getSharedPreferences("difficulties",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("difficulty","medium");
            editor.apply();
        }
        else if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("Hard")){
            SharedPreferences sharedPreferences = Settings.this.getSharedPreferences("difficulties",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("difficulty","hard");
            editor.apply();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
