package com.nanodegree.popularmovies;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nanodegree.popularmovies.service.component.DaggerServiceComponent;
import com.nanodegree.popularmovies.service.component.ServiceComponent;
import com.nanodegree.popularmovies.service.module.ServiceModule;


/**
 * Created by luan_ on 31/05/2017.
 */

public class PopularMovies extends Application {

    private ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Fresco.getImagePipeline().clearCaches();
        serviceComponent = DaggerServiceComponent.builder()
                .appModule(new AppModule(this))
                .serviceModule(new ServiceModule())
                .build();
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
