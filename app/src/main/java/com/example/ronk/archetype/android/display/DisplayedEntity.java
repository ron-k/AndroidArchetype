package com.example.ronk.archetype.android.display;

import android.support.annotation.NonNull;

/**
 * Created by ronk on 10/04/2017.
 */

public interface DisplayedEntity {

    int TYPE_IMAGE = 0;
    int TYPE_VIDEO = 1;

    @NonNull String imageUrl();

    int getItemViewType();
}
