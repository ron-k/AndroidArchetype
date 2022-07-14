package com.ronk.archetype.android.module1.ex202112;

import static org.junit.Assert.assertEquals;

import androidx.core.util.Pair;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListCreatorTest {
    @Test
    public void testPairSum() {
        ListCreator creator = new ListCreator() {
            @Override
            public List<Integer> createInList() {
                throw new UnsupportedOperationException();
            }
        };


        List<Integer> it = Arrays.asList(1, 2, 3, 2);
        Map<Integer, Set<Pair<Integer, Integer>>> actual = creator.getPairSums(it);

        Map<Integer, Set<Pair<Integer, Integer>>> expected = new HashMap<>();
        expected.put(3, Collections.singleton(Pair.create(1, 2)));
        expected.put(4, Set.of(Pair.create(1, 3), Pair.create(2, 2)));
        expected.put(5, Set.of(Pair.create(2, 3)));

        assertEquals(expected, actual);
    }


}