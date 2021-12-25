package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import java.util.List;

public class Solution1Naive implements Solution {
    @Override
    public Pair<Integer, Integer> containsSum(List<Integer> in, int targetSum) {
        for (int i = 0; i < in.size(); i++) {
            Integer first = in.get(i);
            for (int j = i + 1; j < in.size(); j++) {
                Integer second = in.get(j);
                if (first + second == targetSum) {
                    return produceAnswer(first, second);
                }
            }
        }
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return _toString();
    }
}
