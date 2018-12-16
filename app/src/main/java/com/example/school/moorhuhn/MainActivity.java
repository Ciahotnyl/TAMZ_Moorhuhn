package com.example.school.moorhuhn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start_button;
    private Button score_button;
    private Button maps_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        start_button = (Button) findViewById(R.id.Start_button);
        score_button = (Button) findViewById(R.id.Score_button);
        maps_button = (Button) findViewById(R.id.Maps_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityGame();
            }
        });

        score_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityScore();
            }
        });

        maps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMaps();
            }
        });
    }
    public void openActivityGame()
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void openActivityScore()
    {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    public void openActivityMaps()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
