package com.example.school.moorhuhn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;



class GameView extends SurfaceView {

    private SurfaceHolder holder;
    private GameThread gameThread;
    private long lastClick;
    private int player_points = 0;
    private SoundPlayer sound;
    private int ammo = 2;

    private List<Sprite> sprites = new ArrayList<Sprite>();


    public GameView(Context context) {
        super(context);
        sound = new SoundPlayer(context);
        gameThread = new GameThread(this);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprites();
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
    private void createSprites() {
        sprites.add(createSprite(R.drawable.chicken_left_small));
        sprites.add(createSprite(R.drawable.chicken_left_small));
        sprites.add(createSprite(R.drawable.chicken_left_small));
        sprites.add(createSprite(R.drawable.chicken_left_small));
        sprites.add(createSprite(R.drawable.chicken_right_small));
        sprites.add(createSprite(R.drawable.chicken_right_small));
        sprites.add(createSprite(R.drawable.chicken_right_small));
        sprites.add(createSprite(R.drawable.chicken_right_small));
    }

    private Sprite createSprite(int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);

        return new Sprite(this, bmp);
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
                            sprites.remove(sprite);
                            sound.playRoosterSound();
                            player_points += 5;
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

            for (Sprite sprite : sprites) {
                sprite.onDraw(canvas);
            }
        }
    }
}
