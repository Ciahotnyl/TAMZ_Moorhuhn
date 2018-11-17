package com.example.school.moorhuhn;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sprite {

    private int x;
    private int y;
    private int x_speed;
    private int y_speed;

    private Bitmap chicken;
    private GameView gameview;
    private int counter;
    String text;

    public Sprite(GameView gameView, Bitmap pic){
        this.gameview = gameView;
        this.chicken = pic;

        Random rand = new Random(System.currentTimeMillis());
        this.x = rand.nextInt(700) + 25;
        this.y = rand.nextInt(600) + 25;

        Random rand2 = new Random(System.currentTimeMillis());
        this.x_speed = rand2.nextInt(25) + 1;
        this.y_speed = rand2.nextInt(25) + 1;

    }
    private void update(){
        if(x > gameview.getWidth() - chicken.getWidth() - x_speed){
            x_speed = -x_speed;
        }
        if(x + x_speed<0){
            x_speed = 10;
        }
        x += x_speed;

        if(y > gameview.getHeight() - chicken.getHeight() - y_speed){
            y_speed = -y_speed;
        }
        if(y + y_speed<0){
            y_speed = 10;
        }
        y +=  y_speed;

    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(chicken, x, y, null);
    }
    public boolean colliding(float x2, float y2) {
        return x2 > x && x2 < x + chicken.getWidth() && y2 > y && y2 < y + chicken.getHeight();
    }
}
