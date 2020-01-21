package com.example.educationalapp_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Instruction extends AppCompatActivity {

    private ImageView instructionImage;
    private TextView instructionText;
    private int clickCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        instructionImage = (ImageView)findViewById(R.id.instructionImage);
        instructionText = (TextView)findViewById(R.id.instructionText);

    }

    public void clicked(View view){
        clickCounter ++;
        if (clickCounter == 1){
            instructionImage.setImageResource(R.drawable.fish1);
            instructionText.setText("Hit the correct ANSWER with the fish then you will get 10 points");
        }
        else if (clickCounter == 2){
            instructionImage.setImageResource(R.drawable.heart_grey);
            instructionText.setText("If you hit the wrong answer you will lose 1 health");
        }
        else if (clickCounter == 3){
            instructionImage.setImageResource(R.drawable.hearts);
            instructionText.setText("You have 3 hearts, if you loose all the hearts it will be GAME OVER" +
                    "\n HAVE FUN!");
        }
        else if (clickCounter == 4){
            clickCounter = 0;
            finish();
            Intent to_game = new Intent(Instruction.this, game_activity.class);
            startActivity(to_game);
        }
    }
}
