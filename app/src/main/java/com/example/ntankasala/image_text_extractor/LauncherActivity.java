package com.example.ntankasala.image_text_extractor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import javax.inject.Inject;

public class LauncherActivity extends AppCompatActivity {

    public static final int REQUEST_CAMERA_PERMISSION_ID = 100;




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);


        if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M) && (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION_ID);
        } else {
            Intent camera_intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(camera_intent);
            finish();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION_ID:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "CAMERA PERMISSION GRANTED", Toast.LENGTH_LONG).show();
                    Intent camera_intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(camera_intent);
                    finish();

                }
                else {
                    Toast.makeText(this, "CAMERA PERMISSION NOT GRANTED", Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }


}
