package com.example.school.moorhuhn;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        String[] Maps = new String[] { "Easy", "Medium", "Hard" };

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Maps);

        final ListView theListView = (ListView)findViewById(R.id.Maps_listview);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long j) {
                String s = theListView.getItemAtPosition(i).toString();
                openActivityGame(s);
            }
        });
    }
    public void openActivityGame(String diff)
    {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Difficulty", diff.toString());
        startActivity(intent);
    }
}
