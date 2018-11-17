package com.example.school.moorhuhn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameView extends SurfaceView {

    private Bitmap chicken;
    private Bitmap chicken_left;
    private Bitmap chicken_right;
    private SurfaceHolder holder;
    private GameThread gameThread;


    //sprite data
    private int x1 = 50;
    private int y1 = 50;
    private int x_speed = 5;
    private int y_speed = 5;
    private int spriteWidth = 0;
    private int spriteHeight = 0;


    public GameView(Context context) {
        super(context);
        gameThread = new GameThread(this);
        chicken_left = BitmapFactory.decodeResource(getResources(), R.drawable.chicken_left_small);
        chicken_right = BitmapFactory.decodeResource(getResources(), R.drawable.chicken_right_small);
        spriteHeight = chicken_left.getHeight();
        spriteWidth = chicken_left.getWidth();
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
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
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        //check border collision left/right
        if(x1<0 || x1+spriteWidth >= canvas.getWidth()){
            x_speed *= -1;
        }
        //check border collision left/right
        if(y1<0 || y1+ spriteHeight >= canvas.getHeight()){
            y_speed *= -1;
        }
        if(x_speed < 0){
            chicken = chicken_left;
        }
        else{
            chicken = chicken_right;
        }
        x1 += x_speed;
        y1 += y_speed;

        if(canvas!=null)
        {
            canvas.drawColor(Color.BLUE);
            canvas.drawBitmap(chicken, x1, y1, null);
        }
    }
}
