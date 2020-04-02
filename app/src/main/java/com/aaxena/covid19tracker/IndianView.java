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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class IndianView extends AppCompatActivity {
    private Button enter;
    private TextView quote;
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

}
