package com.ronk.archetype.android.module1.ex202112;

/*
@RunWith(Parameterized.class)
public class SolutionTest2 {

    private static final Random PRNG = new Random();
    private static final int TESTS_COUNT = 10;

    private final Solution solution;
    private final List<Integer> inList;


    @Parameterized.Parameters(name = "{0}")
    public static Collection<Scenario> createParameters() {
        List<Scenario> out = new ArrayList<>();
        for (Solution solution : Arrays.asList(
                new Solution3OptimalClassic(),
                new Solution4OptimalMultithreaded(1),
                new Solution4OptimalMultithreaded(8)
        )) {
            out.add(new Scenario(new ListCreatorRandom((int) 1e7), solution));
        }

        return out;
    }

    public SolutionTest2(Scenario scenario) {
        solution = scenario.solution;
        inList = scenario.listCreator.createInList();

    }

    @After
    public void tearDown() throws Exception {
        solution.destroy();
    }

    @Test
    public void benchmark() {
        for (int i = 0; i < TESTS_COUNT; i++) {
            int targetSum = PRNG.nextInt();
            Pair<Integer, Integer> culprits = solution.containsSum(inList, targetSum);
            if (culprits != null) {
                assertEquals("solution sanity", targetSum, culprits.first + culprits.second);
            }

        }
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

 */