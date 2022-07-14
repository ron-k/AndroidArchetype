package com.ronk.archetype.android.module1.ex202112;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * improvement since mk1 - each thread works with local map (no collisions); with merge afterwards
 */
public class Solution4MultithreadedMk2 implements Solution {
    private final int threadsCount;
    private final ExecutorService executor;

    public Solution4MultithreadedMk2(int threadsCount) {
        this.threadsCount = threadsCount;
        executor = Executors.newFixedThreadPool(threadsCount);
//        spinup();
    }

    private void spinup() {
        for (int i = 0; i < threadsCount; i++) {
            executor.submit(() -> {
                SystemClock.sleep(1000);
            });
        }
    }

    private static class Result {
        @Nullable
        private final Pair<Integer, Integer> culprit;

        @Nullable
        private final Map<Integer, AtomicInteger> localMap;

        private static Result found(@Nullable Pair<Integer, Integer> culprit) {
            return new Result(culprit, null);
        }

        private static Result notFound(@Nullable Map<Integer, AtomicInteger> localMap) {
            return new Result(null, localMap);
        }


        private Result(@Nullable Pair<Integer, Integer> culprit, @Nullable Map<Integer, AtomicInteger> localMap) {
            this.culprit = culprit;
            this.localMap = localMap;
        }
    }

    @Nullable
    @Override
    public Pair<Integer, Integer> containsSum(List<Integer> in, int targetSum) {
        List<List<Integer>> windows = createWindows(in);
        ExecutorCompletionService<Result> completion = new ExecutorCompletionService<>(executor);
        for (List<Integer> window : windows) {
            completion.submit(() -> {
                Map<Integer, AtomicInteger> localMap = new HashMap<>(window.size());

                for (Integer i : window) {
                    AtomicInteger counter;
                    if ((counter = localMap.get(i)) == null) {
                        counter = new AtomicInteger();
                        localMap.put(i, counter);
                    }
                    counter.incrementAndGet();
                    Pair<Integer, Integer> ret = checkEntries(targetSum, localMap, i, counter.get());
                    if (ret != null) {
                        return Result.found(ret);
                    }
                }
                return Result.notFound(localMap);

            });
        }

        Map<Integer, AtomicInteger> map = new HashMap<>(in.size());


        try {
            for (int i = 0; i < windows.size(); i++) {
                Result result = completion.take().get();
                Pair<Integer, Integer> res = result.culprit;
                if (res != null) {
                    return res;
                } else {
                    assert result.localMap != null;
                    mergeIntoMap(map, result.localMap);
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new AssertionError(e);
        }

        for (Map.Entry<Integer, AtomicInteger> entry : map.entrySet()) {
            Integer first = entry.getKey();
            int firstCount = entry.getValue().get();
            Pair<Integer, Integer> ret = checkEntries(targetSum, map, first, firstCount);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    private void mergeIntoMap(Map<Integer, AtomicInteger> global, Map<Integer, AtomicInteger> local) {
        for (Map.Entry<Integer, AtomicInteger> entry : local.entrySet()) {
            Integer key = entry.getKey();
            AtomicInteger localCounter = entry.getValue();
            AtomicInteger globalCounter = global.get(key);
            if ((globalCounter == null)) {
                global.put(key, localCounter);
            } else {
                globalCounter.set(globalCounter.get() + localCounter.get());
            }
        }
    }

    @Nullable
    private Pair<Integer, Integer> checkEntries(int targetSum, Map<Integer, AtomicInteger> map, Integer first, int firstCount) {
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

    @Override
    public void destroy() {
        executor.shutdown();
    }

    @VisibleForTesting
    List<List<Integer>> createWindows(List<Integer> in) {
        if (in.isEmpty()) {
            return Collections.singletonList(in);
        }
        int step = Math.max(Math.round((float) in.size() / threadsCount), 1);
        List<List<Integer>> out = new ArrayList<>(threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            int low = Math.min(i * step, in.size());
            if (low == in.size()) {
                break;
            }
            int high = Math.min(low + step, in.size());
            out.add(in.subList(low, high));
        }
        return out;
    }

    @NonNull
    @Override
    public String toString() {
        return "Solution4MultithreadedMk2{" +
                threadsCount +
                '}';
    }
}
