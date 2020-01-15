package com.example.educationalapp_assignment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.TimeUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FishView extends View {

    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;

    private int canvasWidth, canvasHeight;

    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int redX, redY, redSpeed = 20;
    private Paint redPaint = new Paint();

    private int score, lifeCounter;

    private boolean touch = false;

    private Bitmap backgroundImage;

    private Paint scorePaint = new Paint();


    private Bitmap life[] = new Bitmap[2];


    private int firstNum;
    private int secondNum;
    private int result;
    private int falseAnswer;

    private Paint questionPaint = new Paint();

    private Random random;

//    private int counter = 0;

    private MediaPlayer backgroundMusic;
    private MediaPlayer getPointSound;
    private MediaPlayer getLifeSound;
    private MediaPlayer lostLifeSound;

    private SharedPreferences sharedPreferences;
    private String difficulty;

    private int red2X, red2Y, red2Speed = 20;
    private int falseAnswer2;
    private int red3X, red3Y, red3Speed = 20;
    private int falseAnswer3;

    public FishView(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setTextSize(70);
        yellowPaint.setTypeface(Typeface.DEFAULT_BOLD);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.YELLOW);
        redPaint.setTextSize(70);
        redPaint.setTypeface(Typeface.DEFAULT_BOLD);
        redPaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);


        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishY = 550;
        score = 0;
        lifeCounter = 3;

        questionPaint.setColor(Color.WHITE);
        questionPaint.setTextSize(70);
        questionPaint.setTypeface(Typeface.DEFAULT_BOLD);
        questionPaint.setAntiAlias(true);


        random = new Random();
        firstNum = random.nextInt(100);
        secondNum = random.nextInt(100);
        result = firstNum + secondNum;
        falseAnswer = random.nextInt(100);
        if (falseAnswer == result)
            falseAnswer = random.nextInt(100);

        backgroundMusic = MediaPlayer.create(getContext(),R.raw.background_music);
        backgroundMusic.start();

        sharedPreferences = context.getApplicationContext().getSharedPreferences("difficulties", Context.MODE_PRIVATE);
        difficulty = sharedPreferences.getString("difficulty","easy");

        falseAnswer2 = random.nextInt(100);
        if (falseAnswer2 == result)
            falseAnswer2 = random.nextInt(100);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);


        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage,0,0, null);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;

        if (fishY < minFishY){
         fishY = minFishY;
        }
        if (fishY > maxFishY){
            fishY = maxFishY;
        }
        fishSpeed = fishSpeed + 2;


        if (touch){
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        }else {
            canvas.drawBitmap(fish[0], fishX, fishY,null);
        }


        yellowX = yellowX - yellowSpeed;
        if (hitBallChecker(yellowX,yellowY)){
            score = score + 10;
            yellowX = -100;
            firstNum = random.nextInt(100);
            secondNum = random.nextInt(100);
            result = firstNum + secondNum;

            getPointSound = MediaPlayer.create(getContext(),R.raw.gain_points);
            getPointSound.start();
        }

        if (yellowX < 0){
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        canvas.drawText(""+result, yellowX, yellowY, yellowPaint);


        greenX = greenX - greenSpeed;
        if (hitBallChecker(greenX,greenY)){
            lifeCounter++;
            if (lifeCounter > 3)
                lifeCounter = 3;
            greenX = -100;

            getLifeSound = MediaPlayer.create(getContext(),R.raw.gain_life);
            getLifeSound.start();
        }

        if (greenX < 0){
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        if (difficulty.equalsIgnoreCase("easy") || difficulty.equalsIgnoreCase("medium")){
            canvas.drawCircle(greenX, greenY, 20, greenPaint);
        }


        redX = redX - redSpeed;
        if (hitBallChecker(redX,redY)){
            redX = -100;
            lifeCounter--;

            lostLifeSound = MediaPlayer.create(getContext(),R.raw.lost_life);
            lostLifeSound.start();
            
            if (lifeCounter == 0){
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score",score);
                getContext().startActivity(gameOverIntent);
            }
        }

        if (redX < 0){
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
            falseAnswer = random.nextInt(100);
            if (falseAnswer == result)
                falseAnswer = random.nextInt(100);
        }

        canvas.drawText(""+falseAnswer, redX, redY, redPaint);

        if (difficulty.equalsIgnoreCase("medium")){
            red2X = red2X - red2Speed;
            if (hitBallChecker(red2X,red2Y)){
                red2X = -100;
                lifeCounter--;

                lostLifeSound = MediaPlayer.create(getContext(),R.raw.lost_life);
                lostLifeSound.start();

                if (lifeCounter == 0){
                    Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                    Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
                    gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    gameOverIntent.putExtra("score",score);
                    getContext().startActivity(gameOverIntent);
                }
            }

            if (red2X < 0){
                red2X = canvasWidth + 21;
                red2Y = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
                falseAnswer2 = random.nextInt(100);
                if (falseAnswer2 == result)
                    falseAnswer2 = random.nextInt(100);
            }

            canvas.drawText(""+falseAnswer2, red2X, red2Y, redPaint);
        }
        else if (difficulty.equalsIgnoreCase("hard")){
            canvas.drawText(""+falseAnswer2, red2X,red2Y,redPaint);

            red3X = red3X - red3Speed;
            if (hitBallChecker(red3X,red3Y)){
                red3X = -100;
                lifeCounter--;

                lostLifeSound = MediaPlayer.create(getContext(),R.raw.lost_life);
                lostLifeSound.start();

                if (lifeCounter == 0){
                    Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                    Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
                    gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    gameOverIntent.putExtra("score",score);
                    getContext().startActivity(gameOverIntent);
                }
            }

            if (red3X < 0){
                red3X = canvasWidth + 21;
                red3Y = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
                falseAnswer3 = random.nextInt(100);
                if (falseAnswer3 == result)
                    falseAnswer3 = random.nextInt(100);
            }

            canvas.drawText(""+falseAnswer3, red3X, red3Y, redPaint);


        }

        
        canvas.drawText("" + firstNum + "+" + secondNum,425,200,questionPaint);
//        for (int j=0; j<5;j++){
//            counter++;
//            try {
//                Thread.sleep(1500);
//            } catch(InterruptedException e) {
//                System.out.println("got interrupted!");
//            }
//        }
//        if (counter == 5)
//            canvas.drawRect(425,200,700,300,greenPaint);

        canvas.drawText("Score: " + score, 20, 60, scorePaint);



        for (int i = 0; i < 3; i++){
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifeCounter){
                canvas.drawBitmap(life[0], x, y, null);
            }else{
                canvas.drawBitmap(life[1], x, y, null);
            }
        }

    }

    public boolean hitBallChecker(int x, int y){
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;

            fishSpeed = -22;
        }
        return true;
    }
}
