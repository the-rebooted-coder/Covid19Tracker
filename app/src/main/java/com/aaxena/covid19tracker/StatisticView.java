package com.aaxena.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class StatisticView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staistic_view);
        //Toast to Be Patient

        Toast.makeText(StatisticView.this,
                "Be Patient for First Load", Toast.LENGTH_LONG).show();

       //WebView Implementation

        WebView webView = new WebView(this);
        setContentView(webView);
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://google.org/crisisresponse/covid19-map");
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.scrollTo(0, 690);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

    }
}
