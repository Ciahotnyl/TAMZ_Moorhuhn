package com.example.school.moorhuhn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

class GameThread extends Thread{
    private GameView view;
    private boolean running = false;

    public GameThread(GameView view){
        this.view = view;
    }


    public void setRunning(boolean run)
    {
        running = run;
    }

    @Override
    public void run() {
        super.run();
        while(running){
            Canvas c = new Canvas();
            try{
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.onDraw(c);
                }
            }
            finally {
                if(c != null){
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
