package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListCreatorLinear implements ListCreator {
    private final int first;
    private final int len;

    public ListCreatorLinear(int first, int len) {
        this.first = first;
        this.len = len;
    }

    @Override
    public List<Integer> createInList() {
        List<Integer> out = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            out.add(first + i);
        }
        return out;
    }

    @NonNull
    @Override
    public String toString() {
        return "Linear{" +
                "" + first +
                "," + len +
                '}';
    }
}
