package com.example.school.moorhuhn;

import android.graphics.Canvas;

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
            Canvas c = null;
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
