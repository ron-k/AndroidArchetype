package com.ronk.archetype.android;

import androidx.annotation.NonNull;

/**
 * Created by ronk on 10/04/2017.
 */

public class Application extends android.app.Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
