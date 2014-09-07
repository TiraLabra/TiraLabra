package Collections;

import static org.junit.Assert.assertTrue;

public final class CollectionSpeedTest {

    private static final int itemsAdded = 100000;
    private static final int singleTests = 100;
    private static final int containsTests = 1000;
    private static final int addTests = 100000;

    public static void testContains(ICollection<Integer> col, TimeComplexity complexity) {
        for (int i = 0; i < itemsAdded; i++) {
            col.add(i);
        }

        long timeStartSingle = System.nanoTime();
        for (int i = 0; i < singleTests; i++) {
            col.contains(col.size() - 1 - i);
        }
        long singleContain = (System.nanoTime() - timeStartSingle) / singleTests;

        long timeStartMany = System.nanoTime();
        for (int i = 0; i < containsTests; i++) {
            col.contains(col.size() - 1 - i);
        }
        long manyContains = (System.nanoTime() - timeStartMany) / containsTests;

        assertResult(col.size(), manyContains, singleContain, complexity);
    }

    public static void testAdd(ICollection<Integer> col, ICollection<Integer> testAdd, TimeComplexity complexity) {
        long timeStartSingle = System.nanoTime();
        for (int i = 0; i < singleTests; i++) {
            testAdd.add(i);
        }
        long singleAdd = (System.nanoTime() - timeStartSingle) / singleTests;

        long timeStartMany = System.nanoTime();
        for (int i = 0; i < addTests; i++) {
            col.add(i);
        }
        long manyAdded = (System.nanoTime() - timeStartMany) / addTests;

        assertResult(col.size(), manyAdded, singleAdd, complexity);
    }

    private static void assertResult(long size, long many, long single, TimeComplexity complexity) {
        long totalTimeMany = applyComlexity(size, many, complexity);
        long totalTimeSingle = applyComlexity(size, single, complexity);
        long difference = totalTimeMany - totalTimeSingle;
        //if (complexity != TimeComplexity.AverageConstant) {
        assertTrue("Time difference (" + difference + ") must be positive " + single + " " + many + "", difference > 0);
        return;
        //}
        //assertTrue("Time difference (" + difference + ") must atleast above error threshold " + single + " " + many + "", difference > -errorThreshold);
    }

    private static long applyComlexity(long size, long time, TimeComplexity complexity) {
        switch (complexity) {
            case AverageConstant:
                int m = (int) Math.log10(size);
                return time * m;
            case Constant:
                return time;
            case Linear:
                return time * size;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public enum TimeComplexity {

        Constant, Linear, AverageConstant;
    }
}
