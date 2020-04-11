package com.aaxena.covid19tracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Landing2 extends AppCompatActivity {
private Button helplinenos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        helplinenos = findViewById(R.id.helpline);
        helplinenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v7 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v7.vibrate(30);
                Intent intent = new Intent(Landing2.this, Information.class);
                startActivity(intent);
            }
        });
    }
}
