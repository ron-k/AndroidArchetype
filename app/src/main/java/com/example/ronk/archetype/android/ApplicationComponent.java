package com.example.ronk.archetype.android;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ronk on 10/04/2017.
 */

@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
    Application application();

    Picasso picasso();
}
