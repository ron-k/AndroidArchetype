package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.List;

public class ListCreatorLinearShuffled implements ListCreator {
    private final ListCreator wrapped;

    public ListCreatorLinearShuffled(ListCreator wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public List<Integer> createInList() {
        List<Integer> ret = wrapped.createInList();
        Collections.shuffle(ret);
        return ret;
    }

    @Override
    @NonNull
    public String toString() {
        return "Shuffled" + wrapped.toString();
    }
}
