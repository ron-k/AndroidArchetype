package com.ronk.archetype.android;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Created by ronk on 10/04/2017.
 */

public class Application extends android.app.Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @NonNull
    public static Application getInstance() {
        return Objects.requireNonNull(instance);
    }
}
