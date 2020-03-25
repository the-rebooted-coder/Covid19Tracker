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

import com.google.android.material.snackbar.Snackbar;

public class Landing extends AppCompatActivity {
    private Button static_view;
    private Button enter;
    private Button graphic_view;
    private Button bharat_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Kindly Turn On Data Services for First Use", Snackbar.LENGTH_LONG);
        snackbar.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //PiP Mode

        enter = findViewById(R.id.enter_button);
        enter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                Display d = getWindowManager()
                        .getDefaultDisplay();
                Point p = new Point();
                d.getSize(p);
                int width = p.x;
                int height = p.y;

                Rational ratio
                        = new Rational(width, height);
                PictureInPictureParams.Builder
                        pip_Builder
                        = new PictureInPictureParams
                        .Builder();
                pip_Builder.setAspectRatio(ratio).build();
                enterPictureInPictureMode(pip_Builder.build());
            }
        });

        //End of PiP

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
}
