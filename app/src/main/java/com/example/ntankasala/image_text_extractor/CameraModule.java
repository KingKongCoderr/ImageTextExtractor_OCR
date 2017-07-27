package com.example.ntankasala.image_text_extractor;

import android.content.Context;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ntankasala on 5/22/17.
 */

@Module
public class CameraModule{

    private final Context application;

    public CameraModule(Context application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public TextRecognizer getTextrecognizer(){


        TextRecognizer txtrecognizer=new TextRecognizer.Builder(application).build();
        return txtrecognizer;

    }




    @Provides
    @Singleton
    public CameraSource getCamerasource(TextRecognizer txtrecognizer){
      CameraSource  camerasource = new CameraSource.Builder(application, txtrecognizer )
                .setAutoFocusEnabled(true)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .build();

        return camerasource;



    }
}
