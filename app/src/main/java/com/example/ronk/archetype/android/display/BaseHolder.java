package com.example.ronk.archetype.android.display;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ronk on 10/04/2017.
 */

abstract class BaseHolder<T extends DisplayedEntity> extends RecyclerView.ViewHolder {
    public BaseHolder(ViewDataBinding layout) {
        super(layout.getRoot());
    }

    abstract void bind(T e);
}
