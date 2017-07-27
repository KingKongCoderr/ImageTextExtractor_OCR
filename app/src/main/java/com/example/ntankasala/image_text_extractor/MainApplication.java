package com.example.ntankasala.image_text_extractor;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

/**
 * Created by ntankasala on 5/23/17.
 */

public class MainApplication extends Application {

    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .cameraModule(new CameraModule(this)).build();
    }

    public static ApplicationComponent getComponent() {
        return component;
    }

    // See http://blog.sqisland.com/2015/12/mock-application-in-espresso.html for alternative
    // approaches if this doesn't jive for you.
    @VisibleForTesting
    void setComponent(ApplicationComponent component) {
        this.component = component;
    }


}
