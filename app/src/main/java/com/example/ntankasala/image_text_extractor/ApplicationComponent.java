package com.example.ntankasala.image_text_extractor;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by ntankasala on 5/22/17.
 */


@Component(modules = CameraModule.class)
@Singleton
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
