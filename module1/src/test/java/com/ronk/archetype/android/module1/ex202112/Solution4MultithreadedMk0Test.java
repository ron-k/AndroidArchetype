package com.ronk.archetype.android.module1.ex202112;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution4MultithreadedMk0Test {

    private Solution4MultithreadedMk0 sol;


    @Test
    public void createWindows_empty() {
        int threadsCount = 1;
        sol = new Solution4MultithreadedMk0(threadsCount);

        List<Integer> in = ListCreatorEmpty.INSTANCE.createInList();
        List<List<Integer>> windows = sol.createWindows(in);
        assertEquals(Collections.singletonList(Collections.emptyList()), windows);
    }


    @Test
    public void createWindows_1() {
        int threadsCount = 1;
        sol = new Solution4MultithreadedMk0(threadsCount);
        List<Integer> in = new ListCreatorLinear(44, 5).createInList();
        List<List<Integer>> windows = sol.createWindows(in);

        assertEquals(threadsCount, windows.size());
        assertEquals(Collections.singletonList(in), windows);
    }


    @Test
    public void createWindows_threadsGtList() {
        int threadsCount = 101;
        sol = new Solution4MultithreadedMk0(threadsCount);
        List<Integer> in = new ListCreatorLinear(44, 5).createInList();
        List<List<Integer>> windows = sol.createWindows(in);

        assertThat(windows.size(), Matchers.lessThanOrEqualTo(threadsCount));

        List<Integer> union = new ArrayList<>();
        for (List<Integer> window : windows) {
            union.addAll(window);
        }
        assertEquals(in, union);

    }

    @Test
    public void createWindows_threadsLtList() {
        int threadsCount = 3;
        sol = new Solution4MultithreadedMk0(threadsCount);
        List<Integer> in = new ListCreatorRandom(512).createInList();
        List<List<Integer>> windows = sol.createWindows(in);

        assertEquals(threadsCount, windows.size());

        List<Integer> union = new ArrayList<>();
        for (List<Integer> window : windows) {
            union.addAll(window);
        }
        assertEquals(in, union);

    }


}