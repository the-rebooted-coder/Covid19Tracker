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

import java.util.Calendar;

public class IndianView extends AppCompatActivity {
    private Button enter;
    private TextView quote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_indian_view);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 01){
            quote=findViewById(R.id.quote);
            quote.setText("Don't Hoard groceries and essentials. Please ensure that people who are in need don't face a shortage because of you!  ");
        }else if(timeOfDay >= 01 && timeOfDay < 07){
            quote=findViewById(R.id.quote);
            quote.setText("Plan ahead! Take a minute and check how much you have at home. Planning ahead let's you buy exactly what you need! ");
        }
        else if(timeOfDay >= 07 && timeOfDay < 9){
            quote=findViewById(R.id.quote);
            quote.setText("Plan and calculate your essential needs for the next three weeks and get only what is bare minimum needed.");
        }
        else if(timeOfDay >= 9 && timeOfDay < 12){
            quote=findViewById(R.id.quote);
            quote.setText("Be compassionate! Help those in need like the elderly and poor. They are facing a crisis you cannot even imagine!");
        }
        else if(timeOfDay >= 12 && timeOfDay < 15){
            quote=findViewById(R.id.quote);
            quote.setText("Going out to buy essentials? Social Distancing is KEY! Maintain 2 metres distance between each other in the line.");
        }
        else if(timeOfDay >= 15 && timeOfDay < 18){
            quote=findViewById(R.id.quote);
            quote.setText("Lockdown means LOCKDOWN! Avoid going out unless absolutely necessary. Stay safe!");
        }
        else if(timeOfDay >= 18 && timeOfDay < 20){
            quote=findViewById(R.id.quote);
            quote.setText("Stand Against FAKE News and WhatsApp Forwards! Do NOT forward a message until you verify the content it contains.");
        }
        else if(timeOfDay >= 20 && timeOfDay < 22){
            quote=findViewById(R.id.quote);
            quote.setText("Help out your workers and domestic workers by not cutting their salaries. Show the true Indian spirit!  ");
        }
        else if(timeOfDay >= 22 && timeOfDay < 23){
            quote=findViewById(R.id.quote);
            quote.setText("Panic mode : OFF! ESSENTIALS ARE ON!");
        }
        else if(timeOfDay >= 23 && timeOfDay < 0){
            quote=findViewById(R.id.quote);
            quote.setText("Be considerate : While buying essentials remember : You need to share with 130 Crore Others!");
        }
        else {
            quote=findViewById(R.id.quote);
            quote.setText("If you have symptoms and suspect you have coronavirus - reach out to your doctor or call state helplines. Get help.  ");
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
                        "document.getElementsByTagName('footer')[0].style.display='none'; " +
                        "})()");
            }
        });
        webview.loadUrl("https://www.covid19india.org/");
        webview.clearView();
        webview.measure(100,100);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.scrollTo(0, 50);
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
