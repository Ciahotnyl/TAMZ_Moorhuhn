package com.example.school.moorhuhn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class ScoreActivity extends AppCompatActivity {
    private Button menu_button;

    public static final String SHARED_PREFS2 = "sharedPrefs";
    public static final String TEXT_BODY = "text";
    public static final String TEXT_LVL = "lvl";
    TextView easy;
    TextView medium;
    TextView hard;
    private String LOAD_BODY_Easy;
    private String LOAD_LVL_Easy;
    private String LOAD_BODY_Medium;
    private String LOAD_LVL_Medium;
    private String LOAD_BODY_Hard;
    private String LOAD_LVL_Hard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_score);
        easy = (TextView)findViewById(R.id.Easy);
        medium = (TextView)findViewById(R.id.Medium);
        hard = (TextView)findViewById(R.id.Hard);
        loadData();
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
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        LOAD_BODY_Easy = sharedPreferences.getString("Easy_body", "");
        LOAD_LVL_Easy = sharedPreferences.getString("Easy_lvl", "");
        LOAD_BODY_Medium = sharedPreferences.getString("Medium_body", "");
        LOAD_LVL_Medium = sharedPreferences.getString("Medium_lvl", "");
        LOAD_BODY_Hard = sharedPreferences.getString("Hard_body", "");
        LOAD_LVL_Hard = sharedPreferences.getString("Hard_lvl", "");
        updateView();
    }
    public void updateView(){

        easy.setText("Level: "+ LOAD_LVL_Easy + ", body: "+LOAD_BODY_Easy);
        medium.setText("Level: "+ LOAD_LVL_Medium + ", body: "+LOAD_BODY_Medium);
        hard.setText("Level: "+ LOAD_LVL_Hard + ", body: "+LOAD_BODY_Hard);
    }
}
