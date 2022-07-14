package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListCreatorRepeatingConstants implements ListCreator {
    private final int count;
    private final int len;

    public ListCreatorRepeatingConstants(int count, int len) {
        this.count = count;
        this.len = len;
    }


    @Override
    public List<Integer> createInList() {
        List<Integer> out = new ArrayList<>(len);
        Set<Integer> values = new HashSet<>(count);
        while (values.size() < count) {
            values.add(PRNG.nextInt());
        }
        List<Integer> valuesList = new ArrayList<>(values);
        for (int i = 0; i < len; i++) {
            out.add(valuesList.get(PRNG.nextInt(count)));
        }
        return out;
    }

    @Override
    @NonNull
    public String toString() {
        return "RepeatingConstants{" +
                count +
                "," + len +
                '}';
    }
}
