package com.ronk.ex20220714;

import androidx.annotation.NonNull;

import java.util.Objects;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * Created by ronk on 10/04/2017.
 */

public class Application extends android.app.Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new DebugTree());
    }

    @NonNull
    public static Application getInstance() {
        return Objects.requireNonNull(instance);
    }
}
