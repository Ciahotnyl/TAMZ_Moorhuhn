package com.example.school.moorhuhn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoreActivity extends AppCompatActivity {
    private Button menu_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        menu_button = (Button) findViewById(R.id.Back_button);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMenu();
            }
        });
    }
    public void openActivityMenu()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
