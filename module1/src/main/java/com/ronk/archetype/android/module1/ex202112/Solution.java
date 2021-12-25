package com.ronk.archetype.android.module1.ex202112;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import java.util.List;

public interface Solution {
    @Nullable
    Pair<Integer, Integer> containsSum(List<Integer> in, int targetSum);

    @NonNull
    default String _toString() {
        return this.getClass().getSimpleName();
    }

    @NonNull
    @CheckResult
    default Pair<Integer, Integer> produceAnswer(int one, int another) {
        return Pair.create(Math.min(one, another), Math.max(one, another));
    }

    default void destroy() {

    }
}
