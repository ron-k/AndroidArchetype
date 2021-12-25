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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution4OptimalMultithreaded implements Solution {
    private final int threadsCount;
    private final ExecutorService executor;

    public Solution4OptimalMultithreaded(int threadsCount) {
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
        Map<Integer, AtomicInteger> map = prepareMap(in);


        for (Map.Entry<Integer, AtomicInteger> entry : map.entrySet()) {
            Integer first = entry.getKey();
            int firstCount = entry.getValue().get();
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
        }
        return null;
    }

    @Override
    public void destroy() {
        executor.shutdown();
    }

    @NonNull
    private Map<Integer, AtomicInteger> prepareMap(List<Integer> in) {
        Map<Integer, AtomicInteger> map = new ConcurrentHashMap<>(in.size(), 0.75f, threadsCount);
        List<List<Integer>> windows = createWindows(in);

        List<Future<?>> futures = new ArrayList<>(windows.size());
        for (List<Integer> window : windows) {
            futures.add(executor.submit(() -> {
                for (Integer i : window) {
                    AtomicInteger counter;
                    while ((counter = map.get(i)) == null) {
                        map.put(i, new AtomicInteger());
                    }
                    counter.incrementAndGet();
                }

            }));
        }

        try {
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new AssertionError(e);
        }
        return map;
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

    @Override
    public String toString() {
        return "Solution4OptimalMultithreaded{" +
                threadsCount +
                '}';
    }
}
