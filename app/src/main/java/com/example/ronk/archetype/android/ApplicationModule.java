package com.example.ronk.archetype.android;

import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ronk on 10/04/2017.
 */
@Module
public class ApplicationModule {
    private final Application application;

    ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    Application getApplication() {
        return application;
    }

    @Provides
    Picasso picasso(Application application){
        Picasso picasso = Picasso.with(application);
        picasso.setLoggingEnabled(BuildConfig.DEBUG);
        picasso.setIndicatorsEnabled(BuildConfig.DEBUG);
        return picasso;
    }
}
