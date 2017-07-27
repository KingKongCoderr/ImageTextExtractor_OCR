package com.example.ntankasala.image_text_extractor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    //using butterknife to bind views
    @BindView(R.id.camera_surfaceview)
    SurfaceView cameraview;

    @BindView(R.id.camera_tv)
    TextView camera_tv;

    @Inject
    CameraSource camerasource;

    @Inject
    TextRecognizer txtrecognizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainApplication.getComponent().inject(this);
        ButterKnife.bind(this);

        //txtrecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!txtrecognizer.isOperational()) {
            Toast.makeText(this, "Not available", Toast.LENGTH_SHORT).show();
        }
        else {
            /*camerasource = new CameraSource.Builder(getApplicationContext(), txtrecognizer)
                    .setAutoFocusEnabled(true)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .build();*/



            cameraview.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {


                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        camerasource.start(cameraview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                    camerasource.stop();
                }
            });

            txtrecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {

                    final SparseArray<TextBlock> items= detections.getDetectedItems();
                    if(items.size()!=0){
                        camera_tv.post(new Runnable() {
                            @Override
                            public void run() {
                           StringBuilder string_builder=new StringBuilder();
                                for (int i = 0; i < items.size(); i++) {
                                    TextBlock item= items.valueAt(i);
                                    string_builder.append(item.getValue());
                                    string_builder.append("\n");
                                    camera_tv.setText(string_builder.toString());
                                }
                            }
                        });
                    }




                }
            });

        }



    }
    private ApplicationComponent getAppComponent() {
       // MainApplication app=new MainApplication();


        return ( (MainApplication)getApplicationContext()).getComponent();
    }

}
