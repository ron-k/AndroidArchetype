package com.example.ronk.archetype.android.display;

import android.support.annotation.NonNull;

/**
 * Created by ronk on 10/04/2017.
 */

class DisplayedEntityVideo implements DisplayedEntity {
    @NonNull
    @Override
    public String imageUrl() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getItemViewType() {
        return TYPE_VIDEO;
    }
}
