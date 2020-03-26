package com.aaxena.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Poster extends AppCompatActivity {
private int STORAGE_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_poster);

Button buttonRequest = findViewById(R.id.savebtn);
buttonRequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
         if (ContextCompat.checkSelfPermission(Poster.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
             Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
             v2.vibrate(27);
             Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.covidposter);
             Intent share = new Intent(Intent.ACTION_SEND);
             share.setType("image/jpeg");
             ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
             String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                     b, "Title", null);
             Uri imageUri =  Uri.parse(path);
             share.putExtra(Intent.EXTRA_STREAM, imageUri);
             startActivity(Intent.createChooser(share, "Select"));
         }else {
             requestStoragePermission();
         }
    }
});
    }
    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        new AlertDialog.Builder(this)
                .setTitle("Permission Needed to Save")
                .setMessage("Permission for Poster Sharing")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(Poster.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
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
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(27);
            Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.covidposter);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                    b, "Title", null);
            Uri imageUri =  Uri.parse(path);
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(share, "Select"));
        } else{
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(30);
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }
}


  /*

   */