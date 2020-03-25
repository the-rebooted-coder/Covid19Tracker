package com.aaxena.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Licenses extends AppCompatActivity {
private TextView license;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licenses);
        license = findViewById(R.id.license);
        license.setMovementMethod(new ScrollingMovementMethod());
    }
}
