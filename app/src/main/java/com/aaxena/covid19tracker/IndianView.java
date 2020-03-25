package com.aaxena.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class IndianView extends AppCompatActivity {

private Button helpline_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_view);

        final WebView webview = (WebView) findViewById(R.id.browser);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webview.loadUrl("javascript:(function() { " +
                        "document.getElementById('root').getElementsByClassName('Navbar')[0].style.display='none'; " +
                        "document.getElementById('root').getElementsByClassName('table fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('ChoroplethMap fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('timeseries-header fadeInUp')[0].style.display='none'; "+
                        "document.getElementById('root').getElementsByClassName('TimeSeries-Parent fadeInUp')[0].style.display='none'; "+
                        "document.getElementsByTagName('footer')[0].style.display='none'; " +
                        "})()");
            }
        });
        webview.loadUrl("https://www.covid19india.org/");
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.scrollTo(0, 50);
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        helpline_number = findViewById(R.id.helpline_nos);
        helpline_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToHelplinePage();
            }
        });
    }
    private void moveToHelplinePage(){

        Intent intent = new Intent(IndianView.this, HelplineNumbers.class);
        startActivity(intent);
    }
}
