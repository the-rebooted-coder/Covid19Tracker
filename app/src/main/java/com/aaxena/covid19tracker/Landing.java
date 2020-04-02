package com.aaxena.covid19tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.wifi.WifiManager;
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

import com.github.javiersantos.appupdater.AppUpdater;
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
        snackbar.setAction(R.string.wifi_on, new MyUndoListener());
        snackbar.show();
        super.onCreate(savedInstanceState);
        if (isFirstTime()) {
            new AlertDialog.Builder(this)
                    .setTitle("Message from the Developer")
                    .setMessage(R.string.sensitive)
                    .setPositiveButton("I Understand", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(35);
                        }
                    })
                    .create().show();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);

       //App Updater
        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater.start();


        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            textView = findViewById(R.id.textView);
            textView.setText(R.string.morning);
            ayes = findViewById(R.id.homeyes);
            ayes.setVisibility(View.INVISIBLE);
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            textView = findViewById(R.id.textView);
            textView.setText(R.string.afternoon);
            ayes = findViewById(R.id.homeyes);
            ayes.setVisibility(View.INVISIBLE);
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            textView = findViewById(R.id.textView);
            textView.setText(R.string.evening);
            ayes = findViewById(R.id.homeyes);
            ayes.setVisibility(View.INVISIBLE);
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            textView = findViewById(R.id.textView);
            textView.setText(R.string.night);
            quarantine = findViewById(R.id.quaratinetxt);
            quarantine.setText(R.string.question);
            ayes = findViewById(R.id.homeyes);
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

                private void moveToPosterPage() {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(45);
                    Intent intent = new Intent(Landing.this, Poster.class);
                    startActivity(intent);
                }
            });
        } else {
            textView = findViewById(R.id.textView);
            textView.setText("Hello");
            ayes = findViewById(R.id.homeyes);
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

    private void moveToStaticPage() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(38);
        Intent intent = new Intent(Landing.this, StatisticView.class);
        startActivity(intent);
    }

    private void moveToGraphPage() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(38);
        Intent intent = new Intent(Landing.this, GraphicView.class);
        startActivity(intent);
    }

    private void moveToBharatPage() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(38);
        Intent intent = new Intent(Landing.this, IndianView.class);
        startActivity(intent);
    }

    private void moveToLicensePage() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(38);
        Intent intent = new Intent(Landing.this, Licenses.class);
        startActivity(intent);
    }

    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    public class MyUndoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(true);
        }
    }
}
