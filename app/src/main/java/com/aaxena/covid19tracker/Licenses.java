package com.aaxena.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Licenses extends AppCompatActivity {
private TextView license;
private Button update;
private Button settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licenses);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        update = findViewById(R.id.updater);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToUpdatePage();
            }
        });
        license = findViewById(R.id.license);
        license.setMovementMethod(new ScrollingMovementMethod());

    }
    private void moveToUpdatePage() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(38);
        Intent intent = new Intent(Licenses.this, UpdateProvider.class);
        startActivity(intent);
    }
}
