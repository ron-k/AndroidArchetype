package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListCreatorRandom implements ListCreator {
    private final int size;

    public ListCreatorRandom(int size) {
        this.size = size;
    }

    @Override
    public List<Integer> createInList() {
        List<Integer> out = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            out.add(PRNG.nextInt());
        }
        return out;
    }

    @NonNull
    @Override
    public String toString() {
        return "Random{" +
                size +
                '}';
    }
}
