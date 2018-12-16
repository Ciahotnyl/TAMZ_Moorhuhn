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
    TextView body;
    private String LOAD_BODY;
    private String LOAD_LVL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_score);
        body = (TextView)findViewById(R.id.BestScore);
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
        LOAD_BODY = sharedPreferences.getString(TEXT_BODY, "");
        LOAD_LVL = sharedPreferences.getString(TEXT_LVL, "");
        updateView();
    }
    public void updateView(){

        body.setText("Level: "+ LOAD_LVL + ", body: "+LOAD_BODY);
    }
}
