package com.example.school.moorhuhn;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CountDownTimer extends android.os.CountDownTimer {

    long diffSeconds;
    long diffMinutes;
    Canvas c;

    public CountDownTimer(long startTime, long interval, Canvas c)
    {
        super(startTime, interval);
        this.c = c;
    }

    @Override
    public void onFinish()
    {
        //txvDate.setText("0 min 0 sec");
        this.cancel();
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
        diffSeconds = millisUntilFinished / 1000 % 60;
        diffMinutes = millisUntilFinished / (60 * 1000) % 60;

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        c.drawPaint(paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        String resultDate = diffMinutes + ": " + diffSeconds;
        c.drawText("Cas: "+resultDate, 200, 50, paint);

    }
}
