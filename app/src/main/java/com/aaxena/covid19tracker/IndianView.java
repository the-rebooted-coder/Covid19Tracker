package com.aaxena.covid19tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.util.Rational;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.aaxena.covid19tracker.StatisticView.PREFS_NAME;

public class IndianView extends AppCompatActivity {
    private Button enter;
    private TextView quote;
    public static final String PREFS_NAME = "MyPrefsFile1";
    public CheckBox dontShowAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_indian_view);
        Toast.makeText(IndianView.this,
                "Loading Dashboard", Toast.LENGTH_LONG).show();

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 01){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote101);
        }else if(timeOfDay >= 01 && timeOfDay < 07){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote102);
        }
        else if(timeOfDay >= 07 && timeOfDay < 9){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote103);
        }
        else if(timeOfDay >= 9 && timeOfDay < 12){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote104);
        }
        else if(timeOfDay >= 12 && timeOfDay < 15){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote105);
        }
        else if(timeOfDay >= 15 && timeOfDay < 18){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote106);
        }
        else if(timeOfDay >= 18 && timeOfDay < 20){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote107);
        }
        else if(timeOfDay >= 20 && timeOfDay < 22){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote108);
        }
        else if(timeOfDay >= 22 && timeOfDay < 23){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote109);
        }
        else if(timeOfDay >= 23 && timeOfDay < 0){
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote110);
        }
        else {
            quote=findViewById(R.id.quote);
            quote.setText(R.string.quote111);
        }
        //PiP Mode

        enter = findViewById(R.id.enter_button);
        enter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi
                    (api = Build.VERSION_CODES.O)
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
        final WebView webview = (WebView) findViewById(R.id.browser);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webview.loadUrl("javascript:(function() { " +
                        "document.getElementById('root').getElementsByClassName('Navbar')[0].style.display='none'; " +
                        "document.getElementById('root').getElementsByClassName('table fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('ChoroplethMap fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('timeseries-header fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('TimeSeries-Parent fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('Banner fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('svg-parent')[0].style.display='none'; "+
                        "document.getElementsByTagName('footer')[0].style.display='none'; " +
                        "document.getElementsByTagName('home-right')[0].style.display='none'; " +
                        "})()");
            }
        });
        webview.loadUrl("https://www.covid19india.org/");
        webview.clearView();
        webview.measure(100,100);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.scrollTo(0, 224);
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
    }
    @Override
    protected void onResume() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        LayoutInflater adbInflater = LayoutInflater.from(this);
        View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String skipMessage = settings.getString("skipMessage", "NOT checked");

        dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
        adb.setView(eulaLayout);
        adb.setTitle("Before you Proceed");
        adb.setCancelable(false);
        adb.setMessage(Html.fromHtml(getString(R.string.sensitivemessage)));

        adb.setPositiveButton("Show", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";

                if (dontShowAgain.isChecked()) {
                    checkBoxResult = "checked";
                }

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("skipMessage", checkBoxResult);
                editor.commit();

                // Do what you want to do on "OK" action

                return;
            }
        });

        adb.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";

                if (dontShowAgain.isChecked()) {
                    checkBoxResult = "checked";
                }

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("skipMessage", checkBoxResult);
                editor.commit();
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(30);
                Intent intent = new Intent(IndianView.this, Landing.class);
                startActivity(intent);

                return;
            }
        });

        if (!skipMessage.equals("checked")) {
            adb.show();
        }

        super.onResume();
    }
}