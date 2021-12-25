package com.ronk.archetype.android.module1.ex202112;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import com.google.common.base.Stopwatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class SolutionTest {

    private static final Random PRNG = new Random();
    private static final int LIST_SIZE = 256;

    private final Solution solution;
    private final Map<Integer, Set<Pair<Integer, Integer>>> targetSums;
    private final Scenario scenario;
    private final List<Integer> nonContainedSums;
    private final List<Integer> inList;


    @Parameterized.Parameters(name = "{0}")
    public static Collection<Scenario> createParameters() {
        List<Scenario> out = new ArrayList<>();
        for (ListCreator listCreator : Arrays.asList(
                ListCreatorEmpty.INSTANCE,
                new ListCreatorLinear(-42, LIST_SIZE),
                new ListCreatorLinearShuffled(new ListCreatorLinear(-42, LIST_SIZE)),
                new ListCreatorRepeatingConstants(10, LIST_SIZE),
                new ListCreatorRandom(LIST_SIZE)
        )) {
            for (Solution solution : Arrays.asList(
//                    new Solution1Naive(),
//                    new Solution2BinarySearch(),
                    new Solution3ClassicMk0(),
                    new Solution3ClassicMk1(),
                    new Solution4MultithreadedMk0(1),
                    new Solution4MultithreadedMk0(4),
                    new Solution4MultithreadedMk1(4),
                    new Solution4MultithreadedMk2(4)
            )) {
                out.add(new Scenario(listCreator, solution));
            }

        }
        /*
        for (Solution solution : Arrays.asList(
                new Solution3OptimalClassic(),
                new Solution4OptimalMultithreaded(1),
                new Solution4OptimalMultithreaded(8)
        )) {
            out.add(new Scenario(new ListCreatorRandom(5555), solution));
        }
         */
        return out;
    }

    public SolutionTest(Scenario scenario) {
        solution = scenario.solution;
        inList = scenario.listCreator.createInList();
        targetSums = scenario.listCreator.getPairSums(inList);
        this.scenario = scenario;
        int NOT_FOUND_CHECKS_COUNT = targetSums.size();
        nonContainedSums = new ArrayList<>(NOT_FOUND_CHECKS_COUNT);
        int att = NOT_FOUND_CHECKS_COUNT;
        while (att > 0) {
            int maybeSum = randomInt();
            if (!targetSums.containsKey(maybeSum)) {
                nonContainedSums.add(maybeSum);
                att--;
            }
        }
    }

    private static int randomInt() {
        return PRNG.nextInt();
    }


    @Test
    public void testFound() {
        Stopwatch sw = Stopwatch.createStarted();
        for (Map.Entry<Integer, Set<Pair<Integer, Integer>>> entry : targetSums.entrySet()) {
            Integer targetSum = entry.getKey();
            Pair<Integer, Integer> culprits = solution.containsSum(inList, targetSum);
            Set<Pair<Integer, Integer>> solutions = entry.getValue();
            assertNotNull("sum " + targetSum + ": not found. expected: " + solutions, culprits);
            assertThat("sum " + targetSum + ": given solution is valid: ", solutions, hasItem(culprits));
        }
        TestUtils.printf("testFound[%s] - %d", scenario, sw.elapsed(TimeUnit.MILLISECONDS));
    }

    @Test
    public void testNotFound() {
        Stopwatch sw = Stopwatch.createStarted();
        for (Integer nonContainedSum : nonContainedSums) {
            Pair<Integer, Integer> culprits = solution.containsSum(inList, nonContainedSum);
            assertNull("sum " + nonContainedSum + " found while not expected", culprits);
        }
        TestUtils.printf("testNotFound[%s] - %d", scenario, sw.elapsed(TimeUnit.MILLISECONDS));

    }

    private static class Scenario {
        public final ListCreator listCreator;
        public final Solution solution;

        private Scenario(ListCreator listCreator, Solution solution) {
            this.listCreator = listCreator;
            this.solution = solution;
        }

        @NonNull
        @Override
        public String toString() {
            return "" +
                    "" + listCreator +
                    ", " + solution +
                    '}';
        }
    }
}