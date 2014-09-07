package Collections;

import Collections.CollectionSpeedTest.TimeComplexity;
import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class LinkedListTest {

    private LinkedList<String> list;
    private static final String stringBeforeSomeString = "testifmasdfkmasfm";
    private static final String someString = "dsasdadassdaast67h";
    private static final String stringAfterSomeString = "sadssoooooooooooo";
    private static final String toAdd[] = new String[]{"strrrf", "asd", "dsads#", "df9+l", "dssss", "fffff", "fffff", "sdasda", "ww32", "", stringBeforeSomeString, someString, stringAfterSomeString};
    private static final String toAddOne = "asdd asd";
    private static final String toAddMore = "asdsaer64567fg";
    private static final String toAddEvenMore = "8564?DFÅÄSÅ#%Ä";

    @BeforeClass
    public static void setUpStatic() {
        LinkedList<Integer> list = new LinkedList<>();
        int test = 0;
        final int iters = 10000;
        for (int i = 0; i < iters; i++) {
            list.add(i);
        }
        for (int i = 0; i < iters; i++) {
            test += list.contains(i) ? 1 : 0;
            list.remove(i);
            test += list.contains(i) ? 1 : 0;
            list.remove(i);
        }
        System.out.println(test);
    }

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    private void addMany() {
        for (String stringToAdd : toAdd) {
            list.add(stringToAdd);
        }
    }

    private void containsAll() {
        for (String stringAdded : toAdd) {
            if (!list.contains(stringAdded)) {
                fail("String " + stringAdded + " is supposed to be added to list but is not found");
            }
        }
    }

    @Test
    public void testRemoveOne() {
        final String test = "testiåäåäåä";
        list.add(test);
        list.remove(test);
        assertFalse(list.contains(test));
    }

    @Test
    public void testRemove() {
        addMany();
        list.remove(someString);
        assertFalse(list.contains(someString));
    }

    @Test
    public void testRemoveWhenDoesntExist() {
        addMany();
        list.remove("a+????????????????????????????");
        containsAll();
    }

    @Test
    public void testRemoveNull() {
        addMany();
        list.remove(null);
        containsAll();
    }

    @Test
    public void testNullIsNotAdded() {
        list.add(null);
        assertFalse(list.contains(null));
    }

    @Test
    public void testRemoveKeepsPrevious() {
        addMany();
        list.remove(someString);
        assertTrue(list.contains(stringBeforeSomeString));
    }

    @Test
    public void testRemoveKeepsNext() {
        addMany();
        list.remove(someString);
        assertTrue(list.contains(stringAfterSomeString));
    }

    @Test
    public void testAddOne() {
        list.add(toAddOne);
        assertTrue(list.contains(toAddOne));
    }

    @Test
    public void testAddTwo() {
        list.add(toAddOne);
        list.add(toAddMore);
        assertTrue(list.contains(toAddOne));
    }

    @Test
    public void testAddMany() {
        addMany();
        containsAll();
    }

    @Test
    public void testAddManySizeCorrect() {
        addMany();
        assertEquals(toAdd.length, list.size());
    }

    private void addTests() {
        list.add(toAddOne);
        list.add(toAddMore);
        list.add(toAddEvenMore);
    }

    @Test
    public void testRemoveIterating() {
        addTests();
        Iterator<String> listIter = list.iterator();
        while (listIter.hasNext()) {
            String s = listIter.next();
            if (s.equals(toAddMore)) {
                listIter.remove();
            }
        }
        assertFalse(list.contains(toAddMore));
    }

    @Test
    public void testRemoveIteratingOne() {
        list.add(toAddMore);
        Iterator<String> listIter = list.iterator();
        while (listIter.hasNext()) {
            String s = listIter.next();
            if (s.equals(toAddMore)) {
                listIter.remove();
            }
        }
        assertFalse(list.contains(toAddMore));
    }

    @Test
    public void testSpeedAdd() {
        CollectionSpeedTest.testAdd(new LinkedList<Integer>(), new LinkedList<Integer>(), TimeComplexity.Constant);
    }

    @Test
    public void testSpeedContains() {
        CollectionSpeedTest.testContains(new LinkedList<Integer>(), TimeComplexity.Linear);
    }
}
