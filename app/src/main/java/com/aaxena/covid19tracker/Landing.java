package com.aaxena.covid19tracker;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class Landing extends AppCompatActivity {
    private Button static_view;
    private Button graphic_view;
    private Button bharat_view;
    private Button license;
    private TextView textView;
    private TextView quarantine;
    private Button ayes;
    private Button information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFirstTime()) {
          // Dialog Box
            new AlertDialog.Builder(this)
                    .setTitle("An Open Message")
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
        createNotificationChannel();


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
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(38);
               if(haveNetwork()){
                   moveToStaticPage();
               } else if(!haveNetwork())
               {
                   Toast.makeText(Landing.this, "No Connection", Toast.LENGTH_SHORT).show();
               }
            }
        });
        graphic_view = findViewById(R.id.graphical);
        graphic_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(38);
                if(haveNetwork()){
                    moveToGraphPage();
                } else if(!haveNetwork())
                {
                    Toast.makeText(Landing.this, "No Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bharat_view = findViewById(R.id.bharat);
        bharat_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(38);
                if(haveNetwork()){
                    moveToBharatPage();
                } else if(!haveNetwork())
                {
                    Toast.makeText(Landing.this, "No Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        license = findViewById(R.id.license);
        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(38);
                moveToLicensePage();
            }
        });
        information = findViewById(R.id.information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(38);
                moveToInformationPage();
            }
        });
    }

    private void moveToStaticPage() {
        Intent intent = new Intent(Landing.this, StatisticView.class);
        startActivity(intent);
        Intent intent1 = new Intent(Landing.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Landing.this, 0, intent1, 0);

        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();

        long tenSecondsInMillis = 3600000+10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis, pendingIntent);
    }

    private void moveToGraphPage() {
        Intent intent = new Intent(Landing.this, GraphicView.class);
        startActivity(intent);
        Intent intent1 = new Intent(Landing.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Landing.this, 0, intent1, 0);

        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();

        long tenSecondsInMillis = 3600000+10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis, pendingIntent);
    }

    private void moveToBharatPage() {
        Intent intent = new Intent(Landing.this, IndianView.class);
        startActivity(intent);

        //App Notification Manager

        Intent intent1 = new Intent(Landing.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Landing.this, 0, intent1, 0);

        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();

        long tenSecondsInMillis = 300000+10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis, pendingIntent);
    }

    private void moveToLicensePage() {
        Intent intent = new Intent(Landing.this, Licenses.class);
        startActivity(intent);

        //App Notification Manager
        Intent intent1 = new Intent(Landing.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Landing.this, 0, intent1, 0);

        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();

        long tenSecondsInMillis = 4900000+10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis, pendingIntent);
    }

    private void moveToInformationPage() {
        Intent intent = new Intent(Landing.this, Landing2.class);
        startActivity(intent);
        //App Notification Manager

        Intent intent1 = new Intent(Landing.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Landing.this, 0, intent1, 0);
        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();

        long tenSecondsInMillis = 1+10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis, pendingIntent);
    }
    //First Time Run Checker
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
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "Reminder Channel";
            String description = "Channel for Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Reminder", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager  = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }


    //Network Checking Boolean
    private boolean haveNetwork() {
        boolean have_WIFI = false;
        boolean have_MobileData = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_WIFI = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_MobileData = true;
            }
        return have_MobileData||have_WIFI;
        }
    }
