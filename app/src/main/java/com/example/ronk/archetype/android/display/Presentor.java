package com.example.ronk.archetype.android.display;

/**
 * Created by ronk on 10/04/2017.
 */

interface Presentor {

    void onActivityStarted();

    interface View {
        void render(ViewState viewState);
    }
}
