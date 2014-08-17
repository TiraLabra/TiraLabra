package Collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class LinkedListTest {

    private LinkedList<String> list;
    private static final String stringBeforeSomeString = "testifmasdfkmasfm";
    private static final String someString = "dsasdadassdaast67h";
    private static final String stringAfterSomeString = "sadssoooooooooooo";
    private static final String toAdd[] = new String[]{"strrrf", "asd", "dsads#", "df9+l", "dssss", "fffff", "fffff", "sdasda", "ww32", "", stringBeforeSomeString, someString, stringAfterSomeString};

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
        final String toAddOne = "asdd asd";
        list.add(toAddOne);
        assertTrue(list.contains(toAddOne));
    }

    @Test
    public void testAddTwo() {
        final String toAddOne = "asdd asd";
        final String toAddMore = "asdsaer64567fg";
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
}
