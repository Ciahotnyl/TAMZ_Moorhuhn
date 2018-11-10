package com.example.school.moorhuhn;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {
    private Button menu_button;
    private Button start_game_button;
    public int counter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        menu_button = (Button) findViewById(R.id.Menu_button);
        start_game_button = (Button) findViewById(R.id.Start_game_button);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityGame();
            }
        });
        textView = (TextView) findViewById(R.id.CountDown);
        start_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_button.setVisibility(View.INVISIBLE);
                start_game_button.setVisibility(View.INVISIBLE);

                new CountDownTimer(10000,1000){
                    public void onTick(long millisUntilFinished){
                        String text = String.format(Locale.getDefault(), "%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                        textView.setText(text);
                        counter--;
                    }
                    public  void onFinish(){
                        openActivityGame();
                    }
                }.start();
            }
        });
    }
    public void openActivityGame()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
