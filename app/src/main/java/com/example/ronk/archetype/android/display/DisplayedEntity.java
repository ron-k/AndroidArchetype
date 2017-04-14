package com.example.ronk.archetype.android.display;

/**
 * Created by ronk on 10/04/2017.
 */

public interface DisplayedEntity {

    int TYPE_IMAGE = 0;
    int TYPE_VIDEO = 1;

    int getItemViewType();

    <T extends BaseHolder<? extends DisplayedEntity>> void pass(T holder);
}
