package com.ronk.archetype.android;

import androidx.annotation.NonNull;

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
    public Application getApplication() {
        return application;
    }
}
