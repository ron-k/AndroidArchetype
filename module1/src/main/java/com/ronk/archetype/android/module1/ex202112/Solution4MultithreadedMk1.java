package com.ronk.archetype.android.module1.ex202112;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * improvement since mk0 - fast return if found
 */
public class Solution4MultithreadedMk1 implements Solution {
    private final int threadsCount;
    private final ExecutorService executor;

    public Solution4MultithreadedMk1(int threadsCount) {
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


    @Nullable
    @Override
    public Pair<Integer, Integer> containsSum(List<Integer> in, int targetSum) {
        Map<Integer, AtomicInteger> map = new ConcurrentHashMap<>(in.size(), 0.75f, threadsCount);
        List<List<Integer>> windows = createWindows(in);
        ExecutorCompletionService<Pair<Integer, Integer>> completion = new ExecutorCompletionService<>(executor);
        for (List<Integer> window : windows) {
            completion.submit(() -> {
                for (Integer i : window) {
                    AtomicInteger counter;
                    while ((counter = map.get(i)) == null) {
                        map.put(i, new AtomicInteger());
                    }
                    counter.incrementAndGet();
                    Pair<Integer, Integer> ret = checkEntries(targetSum, map, i, counter.get());
                    if (ret != null) {
                        return ret;
                    }
                }
                return null;

            });
        }

        try {
            for (int i = 0; i < windows.size(); i++) {
                Pair<Integer, Integer> res = completion.take().get();
                if (res != null) {
                    return res;
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
        return "Solution4MultithreadedMk1{" +
                threadsCount +
                '}';
    }
}
