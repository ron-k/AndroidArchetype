package com.example.ronk.archetype.android.display;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronk on 14/04/2017.
 */

class ViewState {
    private final List<DisplayedEntity> entities;

    ViewState(@NonNull List<DisplayedEntity> entities) {
        this.entities = new ArrayList<>(entities);
    }

    @NonNull
    public List<DisplayedEntity> getEntities() {
        return entities;
    }
}
