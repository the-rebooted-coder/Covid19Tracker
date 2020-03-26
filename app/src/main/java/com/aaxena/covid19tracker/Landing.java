package com.aaxena.covid19tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Rational;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Landing extends AppCompatActivity {
    private Button static_view;
    private Button graphic_view;
    private Button bharat_view;
    private Button license;
    private TextView textView;
    private TextView quarantine;
    private Button ayes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Kindly Turn Data Services 'On'", Snackbar.LENGTH_LONG);
        snackbar.show();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            textView=findViewById(R.id.textView);
            textView.setText(R.string.morning);
            ayes=findViewById(R.id.homeyes);
            ayes.setVisibility(View.INVISIBLE);
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            textView=findViewById(R.id.textView);
            textView.setText(R.string.afternoon);
            ayes=findViewById(R.id.homeyes);
            ayes.setVisibility(View.INVISIBLE);
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            textView=findViewById(R.id.textView);
            textView.setText(R.string.evening);
            ayes=findViewById(R.id.homeyes);
            ayes.setVisibility(View.INVISIBLE);
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            textView=findViewById(R.id.textView);
            textView.setText(R.string.night);
            quarantine=findViewById(R.id.quaratinetxt);
            quarantine.setText(R.string.question);
            ayes=findViewById(R.id.homeyes);
            ayes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveToPosterPage();
                    Button button = (Button) v;
                    button.setVisibility(View.INVISIBLE);
                    quarantine.setVisibility(View.INVISIBLE);
                    Toast.makeText(Landing.this,
                            "Thanks for Helping!", Toast.LENGTH_LONG).show();
                }
                private void moveToPosterPage(){
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(35);
                    Intent intent = new Intent(Landing.this, Poster.class);
                    startActivity(intent);
                }
            });
        }
       else {
            textView=findViewById(R.id.textView);
            textView.setText("Hello");
            ayes=findViewById(R.id.homeyes);
            ayes.setVisibility(View.INVISIBLE);
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
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(27);
        Intent intent = new Intent(Landing.this, StatisticView.class);
        startActivity(intent);
    }
    private void moveToGraphPage(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(27);
        Intent intent = new Intent(Landing.this, GraphicView.class);
        startActivity(intent);
}
    private void moveToBharatPage(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(27);
        Intent intent = new Intent(Landing.this, IndianView.class);
        startActivity(intent);
    }
    private void moveToLicensePage(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(27);
        Intent intent = new Intent(Landing.this, Licenses.class);
        startActivity(intent);
    }
}
