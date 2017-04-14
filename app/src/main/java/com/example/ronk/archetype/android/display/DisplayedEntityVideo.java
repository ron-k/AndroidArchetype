package com.example.ronk.archetype.android.display;

/**
 * Created by ronk on 10/04/2017.
 */

class DisplayedEntityVideo implements DisplayedEntity {

    @Override
    public int getItemViewType() {
        return TYPE_VIDEO;
    }

    @Override
    public <T extends BaseHolder<? extends DisplayedEntity>> void pass(T holder) {
        ((VideoHolder) holder).bind(this);
    }
}
