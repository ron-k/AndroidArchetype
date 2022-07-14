package com.ronk.archetype.android.module1.ex202112;

import androidx.core.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public interface ListCreator {
    Random PRNG = new Random();


    List<Integer> createInList();

    default Map<Integer, Set<Pair<Integer, Integer>>> getPairSums(List<Integer> list) {
        HashMap<Integer, Set<Pair<Integer, Integer>>> out = new HashMap<>();
        for (int i = 0, listSize = list.size(); i < listSize; i++) {
            Integer first = list.get(i);
            for (int j = i + 1, size = list.size(); j < size; j++) {
                Integer second = list.get(j);
                int sum = first + second;
                Set<Pair<Integer, Integer>> pairs = out.computeIfAbsent(sum, k -> new HashSet<>());
                pairs.add(Pair.create(Math.min(first, second), Math.max(first, second)));
            }
        }
        return out;
    }
}
