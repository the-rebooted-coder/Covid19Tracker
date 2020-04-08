package com.aaxena.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.aaxena.covid19tracker.BuildConfig;

import java.io.ByteArrayOutputStream;

public class UpdateProvider extends AppCompatActivity {
    private int STORAGE_PERMISSION_CODE = 1;
    String versionName = BuildConfig.VERSION_NAME;
    private TextView version_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_provider);
        version_num = findViewById(R.id.version_num);
        version_num.setText(getString(R.string.trackerversion)+versionName);
        if (ContextCompat.checkSelfPermission(UpdateProvider.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            final WebView webview = (WebView) findViewById(R.id.updater);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                }
            });
            webview.loadUrl("https://the-rebooted-coder.github.io/covidrelease/");
            webview.clearView();
            webview.measure(100,100);
            webview.getSettings().setUseWideViewPort(true);
            webview.getSettings().setLoadWithOverviewMode(true);
            webview.scrollTo(0, 700);
            webview.setVerticalScrollBarEnabled(false);
            webview.setHorizontalScrollBarEnabled(false);
            webview.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent,
                                            String contentDisposition, String mimetype,
                                            long contentLength) {
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(url));

                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Covid19Tracker.apk");
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                    Toast.makeText(getApplicationContext(), "Tip: Swipe Down the Notification Panel to See Progress", //To notify the Client that the file is being downloaded
                            Toast.LENGTH_LONG).show();

                }
            });
        }
       else {
            new AlertDialog.Builder(this)
                    .setTitle("Please Grant Download Permissions")
                    .setMessage(R.string.permissionrequester)
                    .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestStoragePermission();
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(35);

                        }
                    })
                    .create().show();
        }}
    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Can't Save Update File")
                    .setMessage("Downloading the available updates are strongly recommended")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(UpdateProvider.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){}
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            final WebView webview = (WebView) findViewById(R.id.updater);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                }
            });
            webview.loadUrl("https://the-rebooted-coder.github.io/covidrelease/");
            webview.clearView();
            webview.measure(100,100);
            webview.getSettings().setUseWideViewPort(true);
            webview.getSettings().setLoadWithOverviewMode(true);
            webview.scrollTo(0, 700);
            webview.setVerticalScrollBarEnabled(false);
            webview.setHorizontalScrollBarEnabled(false);
            webview.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent,
                                            String contentDisposition, String mimetype,
                                            long contentLength) {
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(url));

                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Covid19Tracker.apk");
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                    Toast.makeText(getApplicationContext(), "Tip: Swipe Down the Notification Panel to See Progress", //To notify the Client that the file is being downloaded
                            Toast.LENGTH_LONG).show();

                }
            });
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(27);
        } else{
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(30);
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }
}

