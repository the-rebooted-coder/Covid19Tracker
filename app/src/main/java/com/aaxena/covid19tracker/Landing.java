package com.aaxena.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Landing extends AppCompatActivity {
    private Button graphview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        graphview = findViewById(R.id.graph);
        graphview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToGraphPage();
            }
        });
    }
    private void moveToGraphPage(){

        Intent intent = new Intent(Landing.this, StatisticView.class);
        startActivity(intent);
    }
}
