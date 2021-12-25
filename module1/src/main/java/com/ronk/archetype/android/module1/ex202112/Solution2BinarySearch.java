package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution2BinarySearch implements Solution {
    @Nullable
    @Override
    public Pair<Integer, Integer> containsSum(List<Integer> in, int targetSum) {
        List<Integer> sorted = new ArrayList<>(in);
        Collections.sort(sorted);

        int[] arr = new int[sorted.size()];
        for (int i = 0, sortedSize = sorted.size(); i < sortedSize; i++) {
            Integer val = sorted.get(i);
            arr[i] = val;
        }

        for (int first : arr) {
            int second = targetSum - first;
            int pos = Arrays.binarySearch(arr, second);
            if (second == first) {
                if ((pos > 1 && arr[pos - 1] == first) || (pos < arr.length - 1 && arr[pos + 1] == first)) {
                    return produceAnswer(first, first);
                }
            } else {
                if (pos >= 0) {
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
