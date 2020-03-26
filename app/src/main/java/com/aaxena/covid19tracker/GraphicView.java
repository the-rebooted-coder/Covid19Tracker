package com.aaxena.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class GraphicView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_view);
        //Toast to Be Patient

        Toast.makeText(GraphicView.this,
                "Be Patient while Loading", Toast.LENGTH_LONG).show();

        //WebView Implementation

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.bing.com/covid");
        webView.scrollTo(0, 0);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
    }
}
