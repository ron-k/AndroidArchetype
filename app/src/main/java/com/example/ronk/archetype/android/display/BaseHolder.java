package com.example.ronk.archetype.android.display;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ronk on 10/04/2017.
 */

abstract class BaseHolder extends RecyclerView.ViewHolder{
    public BaseHolder(ViewDataBinding layout) {
        super(layout.getRoot());
    }

    abstract void bind(DisplayedEntity e);
}
