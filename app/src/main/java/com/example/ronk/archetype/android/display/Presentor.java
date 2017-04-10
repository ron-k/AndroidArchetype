package com.example.ronk.archetype.android.display;

import android.support.annotation.NonNull;

/**
 * Created by ronk on 10/04/2017.
 */

interface Presentor {
    @NonNull
    DisplayedEntity getItemAt(int position);

    int getItemCount();

    int getItemViewType(int position);

    void onActivityStarted();
}
