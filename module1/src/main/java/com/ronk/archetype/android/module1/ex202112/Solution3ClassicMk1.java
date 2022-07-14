package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution3ClassicMk1 implements Solution {
    @Nullable
    @Override
    public Pair<Integer, Integer> containsSum(List<Integer> in, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>(in.size());
        for (Integer i : in) {
            Integer counter = map.get(i);
            if (counter == null) {
                counter = 1;
            } else {
                counter++;
            }
            map.put(i, counter);
            Pair<Integer, Integer> ret = checkEntry(targetSum, map, i, counter);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    @Nullable
    private Pair<Integer, Integer> checkEntry(int targetSum, Map<Integer, Integer> map, Integer first, Integer firstCount) {
        int second = targetSum - first;
        if (second == first) {
            if (firstCount > 1) {
                return produceAnswer(first, first);
            }
        } else {
            if (map.containsKey(second)) {
                return produceAnswer(first, second);
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
