package com.example.school.moorhuhn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;


class GameView extends SurfaceView {

    private SurfaceHolder holder;
    private GameThread gameThread;
    private long lastClick;
    private int player_points = 0;
    private int level = 0;
    private int lifes = 1;
    private SoundPlayer sound;
    private int ammo = 2;
    Resources res = getResources();
    Bitmap bg;

    private List<Sprite> sprites = new ArrayList<Sprite>();

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_BODY = "text";
    public static final String TEXT_LVL = "lvl";

    public GameView(Context context) {
        super(context);
        sound = new SoundPlayer(context);
        gameThread = new GameThread(this);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                player_points = 0;
                level = 0;
                lifes = 1;
                ammo = 2;
                createSprites(level);
                gameThread.setRunning(true);
                gameThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while(retry){
                    try{
                        gameThread.join();
                        retry = false;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void createSprites(int lvl) {
        for(int i = 0; i < 5 + lvl; i++){
            sprites.add(createSprite(R.drawable.chicken_left_small, false));
            Random rand = new Random();
            int n = rand.nextInt(10);
            if(n > 1){
                sprites.add(createSprite(R.drawable.bomb, true));
            }
        }
    }

    private Sprite createSprite(int resource, boolean isBomb) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);

        return new Sprite(this, bmp, isBomb);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (System.currentTimeMillis() - lastClick > 500) {
            if(ammo > 0){
                sound.playFireSound();
                ammo--;
                lastClick = System.currentTimeMillis();
                synchronized (getHolder()) {
                    for (int i = sprites.size() - 1; i >= 0; i--) {
                        Sprite sprite = sprites.get(i);
                        if (sprite.colliding(event.getX(), event.getY())) {
                            if(sprites.get(i).isBomb){
                                sound.playBoomSound();
                                lifes -= 1;
                                if(lifes <= 0){
                                    Context context = (Activity)getContext();
                                    saveData(context);
                                    Intent in = new Intent((context),MainActivity.class);
                                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    (context).startActivity(in);
                                }
                            }
                            else{
                                player_points += 5;
                                sound.playRoosterSound();
                            }
                            sprites.remove(sprite);
                            if(sprites.isEmpty()){
                                level += 1;
                                createSprites(level);
                            }
                            break;
                        }
                    }
                }
            }
            else{
                sound.playReloadSound();
                lastClick = System.currentTimeMillis();
                ammo = 2;
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(canvas != null) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("Body: "+player_points, 10, 50, paint);
            canvas.drawText("Lvl: "+level, 300, 50, paint);
            canvas.drawText("Životy: "+lifes, 500, 50, paint);

            for (Sprite sprite : sprites) {
                sprite.onDraw(canvas);
            }
        }
    }
    public void saveData(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences(SHARED_PREFS, c.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT_BODY, String.valueOf(player_points));
        editor.putString(TEXT_LVL, String.valueOf(level));

        editor.commit();
        editor.apply();

        Toast.makeText(c, ("Data saved "+String.valueOf(player_points)+" - "+String.valueOf(level)), Toast.LENGTH_SHORT).show();
    }
}
