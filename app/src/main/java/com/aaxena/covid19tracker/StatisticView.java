package com.aaxena.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class StatisticView extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile1";
    public CheckBox dontShowAgain;
    WebView mWebView;
    private EditText findBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_staistic_view);

        //Toast to Be Patient
        Toast.makeText(StatisticView.this,
                "Loading Statistical Information", Toast.LENGTH_LONG).show();

        //WebView Implementation
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://www.worldometers.info/coronavirus/countries-where-coronavirus-has-spread/");
        mWebView.scrollTo(0, 1300);
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        findBox = (EditText) findViewById(R.id.findBox);
        findBox.setSingleLine(true);
        findBox.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER))) {
                    mWebView.findAll(findBox.getText().toString());

                    try {
                        // Can't use getMethod() as it's a private method
                        for (Method m : WebView.class.getDeclaredMethods()) {
                            if (m.getName().equals("setFindIsUp")) {
                                m.setAccessible(true);
                                m.invoke(mWebView, true);
                                break;
                            }
                        }
                    } catch (Exception ignored) {
                    } finally {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        // check if no view has focus:
                        View vv = getCurrentFocus();
                        if (vv != null) {
                            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }
                }
                return false;
            }
        });

    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    //WarnBox

        @Override
        protected void onResume () {
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
                    Intent intent = new Intent(StatisticView.this, Landing.class);
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
