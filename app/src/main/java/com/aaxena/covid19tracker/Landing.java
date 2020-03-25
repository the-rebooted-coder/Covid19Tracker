package com.aaxena.covid19tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Landing extends AppCompatActivity {
    private Button static_view;
    private Button graphic_view;
    private Button bharat_view;
    private Button license;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Kindly Turn On Data Services for First Use", Snackbar.LENGTH_LONG);
        snackbar.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss a");
        String formattedDate = df.format(c.getTime());

        if (formattedDate.contains("AM")) {
            textView=findViewById(R.id.textView);
            textView.setText("Good Morning");

        } else {
            textView=findViewById(R.id.textView);
            textView.setText("Good Evening.");
        }

        static_view = findViewById(R.id.statistical);
        static_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToStaticPage();
            }
        });
        graphic_view = findViewById(R.id.graphical);
        graphic_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToGraphPage();
            }
        });
        bharat_view = findViewById(R.id.bharat);
        bharat_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToBharatPage();
            }
        });
        license = findViewById(R.id.license);
        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToLicensePage();
            }
        });
    }
    private void moveToStaticPage(){

        Intent intent = new Intent(Landing.this, StatisticView.class);
        startActivity(intent);
    }
    private void moveToGraphPage(){

        Intent intent = new Intent(Landing.this, GraphicView.class);
        startActivity(intent);
}
    private void moveToBharatPage(){

        Intent intent = new Intent(Landing.this, IndianView.class);
        startActivity(intent);
    }
    private void moveToLicensePage(){

        Intent intent = new Intent(Landing.this, Licenses.class);
        startActivity(intent);
    }
}
