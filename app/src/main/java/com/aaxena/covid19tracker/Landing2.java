package com.aaxena.covid19tracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;


public class Landing2 extends AppCompatActivity {
    private int STORAGE_PERMISSION_CODE = 2;
    private Button helplinenos;
    private Button call;
            TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textView = findViewById(R.id.textView5);
        new MaterialIntroView.Builder(this)
                .enableDotAnimation(true)
                .enableIcon(false)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(500)
                .enableFadeAnimation(true)
                .performClick(true)
                .setInfoText("Hi There! Click this card and see what happens.")
                .setShape(ShapeType.RECTANGLE)
                .setTarget(textView)
                .setUsageId("intro_card") //THIS SHOULD BE UNIQUE ID
                .setMaskColor(getResources().getColor(R.color.bluetrans))
                .show();

        call = findViewById(R.id.callto);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator v7 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v7.vibrate(39);
                if (ActivityCompat.checkSelfPermission(Landing2.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:+911123978046"));
                    startActivity(callIntent);
                } else
                {
                    requestCallPermission();
                }
            }
        });

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

    private void requestCallPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},STORAGE_PERMISSION_CODE);
    }
}
