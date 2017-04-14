package com.example.ronk.archetype.android.display;

import android.support.annotation.NonNull;

/**
 * Created by ronk on 10/04/2017.
 */
class DisplayedEntityImage implements DisplayedEntity {
    private final String url;

    public DisplayedEntityImage(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public String imageUrl() {
        return url;
    }

    @Override
    public int getItemViewType() {
        return TYPE_IMAGE;
    }

    @Override
    public <T extends BaseHolder<? extends DisplayedEntity>> void pass(T holder) {
        ((ImageHolder) holder).bind(this);
    }

}
