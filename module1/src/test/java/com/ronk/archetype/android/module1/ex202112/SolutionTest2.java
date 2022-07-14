//package com.ronk.archetype.android.module1.ex202112;
//
//
//import static org.junit.Assert.assertEquals;
//
//import androidx.annotation.NonNull;
//import androidx.core.util.Pair;
//
//import com.google.common.base.Stopwatch;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//
//@RunWith(Parameterized.class)
//public class SolutionTest2 {
//
//    private static final Random PRNG = new Random();
//    private static final int TESTS_COUNT = 100;
//
//    private final Solution solution;
//    private final List<Integer> inList;
//    private final Scenario scenario;
//
//
//    @Parameterized.Parameters(name = "{0}")
//    public static Collection<Scenario> createParameters() {
//        List<Scenario> out = new ArrayList<>();
//        for (Solution solution : Arrays.asList(
//                new Solution3ClassicMk1(),
////                new Solution4MultithreadedMk0(4),
//                new Solution4MultithreadedMk1(8),
//                new Solution4MultithreadedMk2(8)
//        )) {
//            out.add(new Scenario(new ListCreatorRandom((int) 1e7), solution));
//        }
//
//        return out;
//    }
//
//    public SolutionTest2(Scenario scenario) {
//        solution = scenario.solution;
//        inList = scenario.listCreator.createInList();
//
//        this.scenario = scenario;
//    }
//
//    @After
//    public void tearDown() throws Exception{
//        solution.destroy();
//        System.gc();
//        Thread.sleep(7000);
//    }
//
//    @Test
//    public void benchmark() {
//        Stopwatch sw = Stopwatch.createStarted();
//        for (int i = 0; i < TESTS_COUNT; i++) {
//            int targetSum = PRNG.nextInt();
//            Pair<Integer, Integer> culprits = solution.containsSum(inList, targetSum);
//            if (culprits != null) {
//                assertEquals("solution sanity", targetSum, culprits.first + culprits.second);
//            }
//        }
//        TestUtils.printf("testNotFound[%s] - %d",scenario,sw.elapsed(TimeUnit.MILLISECONDS));
//
//    }
//
//    private static class Scenario {
//        public final ListCreator listCreator;
//        public final Solution solution;
//
//        private Scenario(ListCreator listCreator, Solution solution) {
//            this.listCreator = listCreator;
//            this.solution = solution;
//        }
//
//        @NonNull
//        @Override
//        public String toString() {
//            return "" +
//                    "" + listCreator +
//                    ", " + solution +
//                    '}';
//        }
//    }
//}
//
